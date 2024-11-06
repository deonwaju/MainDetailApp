package com.deontch.feature.products.data.repo

import app.cash.turbine.test
import com.deontch.core.common.test.DummyData
import com.deontch.core.database.dao.ProductsDao
import com.deontch.core.network.ProductsAPIService
import com.deontch.core.network.model.JsonProductsResponse
import com.deontch.feature.products.data.mapper.ProductsMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {

    @MockK
    private lateinit var productsDao: ProductsDao

    @MockK
    private lateinit var productsApi: ProductsAPIService

    private val mapper: ProductsMapper = ProductsMapper()

    private lateinit var repository: ProductsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ProductsRepositoryImpl(productsDao, productsApi, mapper)
    }

    @Test
    fun `given products are present in the database when getProducts is called, then products are streamed`() =
        runTest {
            val dbProducts = DummyData.productEntity
            val uiProducts = DummyData.getDummyProducts()

            // Define mock behavior
            every { productsDao.getAllProducts() } returns flowOf(listOf(dbProducts))
            coEvery { productsApi.getProducts() } returns JsonProductsResponse(listOf(DummyData.jsonProduct))
            coEvery { productsDao.insertProducts(any()) } returns Unit

            repository.getProducts().test {
                val emitted = awaitItem()
                assertThat(emitted).isEqualTo(uiProducts)
                awaitComplete()
            }

            coVerify(exactly = 1) { productsApi.getProducts() }
            coVerify { productsDao.insertProducts(any()) }
        }

    @Test
    fun `given product ID when getProductDetailById is called, then the product detail is returned`() =
        runTest {
            val productId = 1L
            val dbProducts = DummyData.productEntity
            val uiProducts = DummyData.getDummyProducts()

            // Define mock behavior
            every { productsDao.getProductById(productId) } returns flowOf(dbProducts)

            val result = repository.getProductDetailById(productId).first()

            assertThat(result).isEqualTo(uiProducts)
        }

    @Test
    fun `given products are fetched from API when getProducts is called, then products are inserted into the database`() =
        runTest {
            val apiProducts = DummyData.getDummyProducts()

            // Define mock behavior for API call
            coEvery { productsApi.getProducts() } returns JsonProductsResponse(listOf(DummyData.jsonProduct))
            coEvery { productsDao.insertProducts(any()) } returns Unit

            repository.getProducts().collect{
                assertThat(it).isEqualTo(apiProducts)
                assertThat(it).isNotEmpty()
            }

            // Verify that the products were inserted into the database
            coVerify { productsDao.insertProducts(any()) }
        }
}

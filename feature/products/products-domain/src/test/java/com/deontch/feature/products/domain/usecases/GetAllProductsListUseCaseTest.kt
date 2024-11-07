package com.deontch.feature.products.domain.usecases

import app.cash.turbine.test
import com.deontch.core.common.domain.utils.DispatcherProvider
import com.deontch.core.common.test.DummyData
import com.deontch.core.common.test.TestDispatcherProviderImpl
import com.deontch.feature.products.domain.repository.IProductsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetAllProductsListUseCaseTest {

    @MockK
    private lateinit var productsRepository: IProductsRepository

    private val dispatcherProvider: DispatcherProvider = TestDispatcherProviderImpl()

    private lateinit var getAllProductsListUseCase: GetAllProductsListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllProductsListUseCase = GetAllProductsListUseCase(productsRepository, dispatcherProvider)
    }

    @Test
    fun `when getAllProductsListUseCase is invoked and repository contains product data, then it should emit correct product list`() = runTest {
        val productList = DummyData.getDummyProducts()
        coEvery { productsRepository.getProducts() } returns flowOf(productList)

        getAllProductsListUseCase(Unit).test {
            assertThat(awaitItem()).isEqualTo(productList)
            awaitComplete()
        }

        verify(exactly = 1) { productsRepository.getProducts() }
    }

    @Test
    fun `when getAllProductsListUseCase is invoked and repository fails to fetch product data, then it should emit an error`() = runTest {
        val exception = IllegalStateException("Failed to fetch product data")
        coEvery { productsRepository.getProducts() } returns flow { throw exception }

        getAllProductsListUseCase(Unit).test {
            val error = awaitError()
            assertThat(error).isInstanceOf(IllegalStateException::class.java)
        }

        verify(exactly = 1) { productsRepository.getProducts() }
    }
}

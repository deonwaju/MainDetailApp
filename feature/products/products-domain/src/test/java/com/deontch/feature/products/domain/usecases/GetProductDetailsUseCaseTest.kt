package com.deontch.feature.products.domain.usecases

import app.cash.turbine.test
import com.deontch.core.common.domain.usecases.NoParamsException
import com.deontch.core.common.domain.utils.DispatcherProvider
import com.deontch.core.common.test.DummyData
import com.deontch.core.common.test.TestDispatcherProviderImpl
import com.deontch.feature.products.domain.repository.IProductsRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class GetProductDetailsUseCaseTest {

    @MockK
    private lateinit var productsRepository: IProductsRepository
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProviderImpl()
    private lateinit var getProductDetailsUseCase: GetProductDetailsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getProductDetailsUseCase = GetProductDetailsUseCase(productsRepository, dispatcherProvider)
    }

    @Test
    fun `when getProductDetailsUseCase is invoked with a valid product ID, then it should emit the correct product details`() =
        runTest {
            val productId = 1L
            val productDetail =
                DummyData.getDummyProduct1() // This should be a dummy product object

            coEvery { productsRepository.getProductDetailById(productId) } returns flowOf(
                productDetail
            )

            getProductDetailsUseCase(productId).test {
                assertThat(awaitItem()).isEqualTo(productDetail)
                awaitComplete()
            }
        }

    @Test
    fun `when getProductDetailsUseCase is invoked with an invalid product ID, then it should emit an error`() =
        runTest {
            val productId = 1L
            val exception = IllegalStateException("Product not found")

            coEvery { productsRepository.getProductDetailById(productId) } returns flow { throw exception }

            getProductDetailsUseCase(productId).test {
                val error = awaitError()
                assertThat(error).isInstanceOf(IllegalStateException::class.java)
            }
        }

    @Test
    fun `when getProductDetailsUseCase is invoked with no params, then it should throw NoParamsException`() =
        runTest {
            assertThrows(NoParamsException::class.java) {
                getProductDetailsUseCase(null)
            }
        }
}

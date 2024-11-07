package com.deontch.feature.product.details

import app.cash.turbine.test
import com.deontch.core.common.test.DummyData
import com.deontch.feature.product.details.presentation.ProductDetailsUiState
import com.deontch.feature.product.details.presentation.ProductDetailsViewmodel
import com.deontch.feature.products.domain.usecases.GetProductDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductDetailsViewModelTest {

    private lateinit var viewModel: ProductDetailsViewmodel
    private val getProductDetailsUseCase: GetProductDetailsUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    private val sampleProduct = DummyData.getDummyProduct()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductDetailsViewmodel(getProductDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getProductDetails emits Loading and then Success`() = runTest {
        coEvery { getProductDetailsUseCase(1L) } returns flowOf(sampleProduct)

        viewModel.states.test {
            viewModel.getProductDetails(1L)

            assertEquals(ProductDetailsUiState.Idle, awaitItem())
            assertEquals(ProductDetailsUiState.Loading, awaitItem())
            assertEquals(ProductDetailsUiState.Success(sampleProduct), awaitItem())
        }
    }

    @Test
    fun `getProductDetails initially emits Idle state`() = runTest {
        viewModel.states.test {
            assertEquals(ProductDetailsUiState.Idle, awaitItem())
        }
    }
}
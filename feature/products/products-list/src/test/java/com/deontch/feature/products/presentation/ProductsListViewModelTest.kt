package com.deontch.feature.products.presentation

import app.cash.turbine.test
import com.deontch.core.common.test.DummyData
import com.deontch.core.modules.Product
import com.deontch.feature.products.domain.usecases.GetAllProductsListUseCase
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
class ProductsListViewModelTest {

    private lateinit var viewModel: ProductsListViewModel
    private val getProductsUseCase: GetAllProductsListUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    private val sampleProducts = listOf(
        DummyData.getDummyProduct(),
        DummyData.getDummyProduct1(),
        DummyData.getDummyProduct2(),
    )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProductsListViewModel(getProductsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset to the original Main dispatcher
    }

    @Test
    fun `getProducts emits Loading and then Success`() = runTest {
        coEvery { getProductsUseCase() } returns flowOf(sampleProducts)

        viewModel.uiState.test {
            viewModel.getProducts()

            assertEquals(ProductsListUiState.Initial, awaitItem())
            assertEquals(ProductsListUiState.Loading, awaitItem())
            assertEquals(ProductsListUiState.Success(sampleProducts), awaitItem())
        }

        viewModel.filteredProducts.test {
            assertEquals(sampleProducts, awaitItem())
        }
    }

    @Test
    fun `getProducts emits Error when use case fails`() = runTest {
        val exceptionMessage = "An error occurred"
        coEvery { getProductsUseCase() } throws Exception(exceptionMessage)

        viewModel.uiState.test {
            viewModel.getProducts()

            assertEquals(ProductsListUiState.Initial, awaitItem())
            assertEquals(ProductsListUiState.Loading, awaitItem())
            assertEquals(ProductsListUiState.Error(exceptionMessage), awaitItem())
        }
    }

    @Test
    fun `onSearchQueryChanged filters products by title and colour`() = runTest {
        coEvery { getProductsUseCase() } returns flowOf(sampleProducts)
        viewModel.getProducts()

        viewModel.filteredProducts.test {
            skipItems(1)

            viewModel.onSearchQueryChanged("Product 2")
            assertEquals(
                listOf(sampleProducts[1]),
                awaitItem()
            )

            viewModel.onSearchQueryChanged("Product 4")
            assertEquals(
                listOf(sampleProducts[0]),
                awaitItem()
            )

            viewModel.onSearchQueryChanged("Product")
            assertEquals(
                listOf(sampleProducts[0], sampleProducts[1], sampleProducts[2]),
                awaitItem()
            )

            viewModel.onSearchQueryChanged("Nonexistent")
            assertEquals(
                emptyList<Product>(),
                awaitItem()
            )
        }
    }
}

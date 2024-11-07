package com.deontch.feature.products

import app.cash.turbine.test
import com.deontch.core.common.test.DummyData
import com.deontch.feature.products.domain.usecases.GetAllProductsListUseCase
import com.deontch.feature.products.presentation.ProductsListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductsListViewModelTest {

    private lateinit var viewModel: ProductsListViewModel
    private val getProductsUseCase: GetAllProductsListUseCase = mockk()

    private val sampleProducts = DummyData.getDummyProducts()
    @Before
    fun setup() {
        viewModel = ProductsListViewModel(getProductsUseCase)
    }

    @Test
    fun `getProducts emits Loading and then Success`() = runTest {
        coEvery { getProductsUseCase() } returns flowOf(sampleProducts)

        viewModel.uiState.test {
            viewModel.getProducts()

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

            assertEquals(ProductsListUiState.Loading, awaitItem())
            assertEquals(ProductsListUiState.Error(exceptionMessage), awaitItem())
        }
    }

    @Test
    fun `onSearchQueryChanged filters products by title and colour`() = runTest {
        // Assume that getProducts has been called and populated allProducts
        coEvery { getProductsUseCase() } returns flowOf(sampleProducts)
        viewModel.getProducts()

        viewModel.filteredProducts.test {
            // Ignore initial item since it's already populated by getProducts
            skipItems(1)

            viewModel.onSearchQueryChanged("Product")
            assertEquals(
                listOf(sampleProducts[0], sampleProducts[1], sampleProducts[2]),
                awaitItem()
            )

            viewModel.onSearchQueryChanged("Two")
            assertEquals(
                listOf(sampleProducts[1]),
                awaitItem()
            )

            viewModel.onSearchQueryChanged("Red")
            assertEquals(
                listOf(sampleProducts[0]),
                awaitItem()
            )
        }
    }
}

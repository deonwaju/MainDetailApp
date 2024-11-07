package com.deontch.feature.products.presentation

import androidx.compose.runtime.Immutable
import com.deontch.core.modules.Product

@Immutable
sealed class ProductsListUiState {
    object Initial : ProductsListUiState()
    object Loading : ProductsListUiState()
    data class Success(val products: List<Product>) : ProductsListUiState()
    data class Error(val message: String) : ProductsListUiState()
}

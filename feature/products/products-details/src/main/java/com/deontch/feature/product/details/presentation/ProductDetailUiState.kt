package com.deontch.feature.product.details.presentation

import com.deontch.core.modules.Product

sealed class ProductDetailsUiState {
    object Idle : ProductDetailsUiState()
    object Loading : ProductDetailsUiState()
    data class Success(val data: Product) : ProductDetailsUiState()
}
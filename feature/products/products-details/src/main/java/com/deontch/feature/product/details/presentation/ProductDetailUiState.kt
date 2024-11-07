package com.deontch.feature.product.details.presentation

import androidx.compose.runtime.Immutable
import com.deontch.core.modules.Product

@Immutable
sealed class ProductDetailsUiState {
    object Idle : ProductDetailsUiState()
    object Loading : ProductDetailsUiState()
    data class Success(val data: Product) : ProductDetailsUiState()
}
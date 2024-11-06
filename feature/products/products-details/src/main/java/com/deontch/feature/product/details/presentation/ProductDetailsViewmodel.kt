package com.deontch.feature.product.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deontch.core.modules.Product
import com.deontch.feature.products.domain.usecases.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewmodel @Inject constructor(
    private val getProductDetailsUseCase: GetProductDetailsUseCase
): ViewModel() {
    private val _states = MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Idle)
    val states = _states.asStateFlow()

    fun getProductDetails(productId: Long) {
        _states.value = ProductDetailsUiState.Loading
        viewModelScope.launch {
            getProductDetailsUseCase(productId).collect { result ->
                _states.value = ProductDetailsUiState.Success(result)
            }
        }
    }
}

sealed class ProductDetailsUiState {
    object Idle : ProductDetailsUiState()
    object Loading : ProductDetailsUiState()
    data class Success(val data: Product) : ProductDetailsUiState()
}

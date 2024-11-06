package com.deontch.feature.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deontch.feature.products.ProductsListUiState
import com.deontch.feature.products.domain.usecases.GetAllProductsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(
    private val getProductsUseCase: GetAllProductsListUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<ProductsListUiState>(ProductsListUiState.Initial)
    val uiState: StateFlow<ProductsListUiState> = _uiState

    fun getProducts() {
        _uiState.value = ProductsListUiState.Loading
        viewModelScope.launch {
            try {
                getProductsUseCase().collect {
                    _uiState.value = ProductsListUiState.Success(it)
                }
            } catch (e: Exception) {
                _uiState.value = ProductsListUiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}
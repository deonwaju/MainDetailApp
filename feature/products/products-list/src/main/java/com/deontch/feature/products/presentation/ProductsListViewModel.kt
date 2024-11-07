package com.deontch.feature.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deontch.core.modules.Product
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

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> get() = _filteredProducts

    private var allProducts = emptyList<Product>()

    fun getProducts() {
        _uiState.value = ProductsListUiState.Loading
        viewModelScope.launch {
            try {
                getProductsUseCase().collect { products ->
                    allProducts = products
                    _filteredProducts.value = products
                    _uiState.value = ProductsListUiState.Success(products)
                }
            } catch (e: Exception) {
                _uiState.value = ProductsListUiState.Error(e.message ?: "An error occurred")
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _filteredProducts.value = allProducts.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.colour.contains(query, ignoreCase = true)
        }
    }
}
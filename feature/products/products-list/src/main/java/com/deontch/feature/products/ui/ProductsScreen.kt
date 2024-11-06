package com.deontch.feature.products.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.deontch.core.modules.Product
import com.deontch.feature.products.ProductsListUiState
import com.deontch.feature.products.presentation.ProductsListViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductsScreen(productsViewModel: ProductsListViewModel = hiltViewModel()) {

    val uiState by productsViewModel.uiState.collectAsState()

    when (uiState) {
        is ProductsListUiState.Initial -> {
            Text(text = "Welcome to the Products Screen!")
        }
        is ProductsListUiState.Loading -> {
            CircularProgressIndicator()
        }
        is ProductsListUiState.Success -> {
            ProductList(products = (uiState as ProductsListUiState.Success).products)
        }

        is ProductsListUiState.Error -> {
            ErrorView(errorMessage = (uiState as ProductsListUiState.Error).message)
        }
    }

    LaunchedEffect(Unit) {
        productsViewModel.getProducts()
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            Text(text = products.map { it.title }.toString())
        }
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Text(text = "Error: $errorMessage", color = Color.Red)
}

package com.deontch.feature.product.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deontch.feature.product.details.nav.ProductDetailsNav
import com.deontch.feature.product.details.presentation.ProductDetailsUiState
import com.deontch.feature.product.details.presentation.ProductDetailsViewmodel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ProductsDetailsScreen(
    productId: Long,
    viewModel: ProductDetailsViewmodel = hiltViewModel(),
    navigator: ProductDetailsNav,
) {
    val uiState by viewModel.states.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        println("ProductDetailsScreen: $productId")
        viewModel.getProductDetails(productId)
    }

    ProductsDetailsScreenContent(
        state = uiState,
        onNavigateUp = navigator::onBackPressed,
    )
}

@Composable
fun ProductsDetailsScreenContent(
    state: ProductDetailsUiState,
    onNavigateUp: () -> Unit
) {
    println("product detail State::::${state}")
}

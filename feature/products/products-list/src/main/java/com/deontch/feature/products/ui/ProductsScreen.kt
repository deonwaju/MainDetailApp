package com.deontch.feature.products.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.aliumujib.all.breeds.R
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
fun ProductList(
    products: List<Product>,

) {
    LazyColumn {
        items(products) { product ->
            Column {
                val imageLoader = LocalContext.current.imageLoader

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.featuredMedia.src)
                        .placeholder(io.eyram.iconsax.R.drawable.broken_icon_1)
                        .error(io.eyram.iconsax.R.drawable.broken_icon_1)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Artwork",
                    imageLoader = imageLoader,
                    modifier = Modifier
                        .height(150.dp)
                        .clip(RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp)),
                    contentScale = ContentScale.Crop,
                )

                Text(text = product.title)
                Text(text = product.colour)
                product.labels?.let {
                    Text(text = it)
                }
            }

        }
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Text(text = "Error: $errorMessage", color = Color.Red)
}

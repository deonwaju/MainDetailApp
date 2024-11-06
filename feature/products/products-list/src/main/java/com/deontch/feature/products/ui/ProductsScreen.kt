package com.deontch.feature.products.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.deontch.common.design.components.HtmlText
import com.deontch.core.common.util.toMonetaryString
import com.deontch.core.modules.Product
import com.deontch.feature.products.ProductsListUiState
import com.deontch.feature.products.nav.ProductsListNavigator
import com.deontch.feature.products.presentation.ProductsListViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductsScreen(
    productsViewModel: ProductsListViewModel = hiltViewModel(),
    navigator: ProductsListNavigator,
) {

    val uiState by productsViewModel.uiState.collectAsState()

    when (uiState) {
        is ProductsListUiState.Initial -> {
            Text(text = "Welcome to the Products Screen!")
        }

        is ProductsListUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ProductsListUiState.Success -> {
            ProductList(
                products = (uiState as ProductsListUiState.Success).products,
                onItemClick = { product ->
                    navigator.goToDetails(product.id)
                }
            )
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
    onItemClick: (Product) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        var isListView by remember { mutableStateOf(false) }

        val buttonText = if (isListView) "Switch to Grid View" else "Switch to List View"

        Button(
            onClick = { isListView = !isListView },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(buttonText)
        }

        if (isListView) {
            ProductListView(products = products, onItemClick)
        } else {
            ProductGridView(products = products, onItemClick)
        }
    }
}

@Composable
fun ProductGridView(
    products: List<Product>,
    onItemClick: (Product) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(products) { product ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(product) }
            ) {
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
                        .height(200.dp)
                        .clip(RoundedCornerShape(topEnd = 5.dp, topStart = 5.dp)),
                    contentScale = ContentScale.Fit,
                )
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = product.colour,
                    style = MaterialTheme.typography.bodySmall
                )
                product.labels?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = product.price.toMonetaryString(),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun ProductListView(
    products: List<Product>,
    onItemClick: (Product) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(products) { product ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(product) }
            ) {
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

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.headlineSmall

                )
                Text(
                    text = product.colour,
                    style = MaterialTheme.typography.bodySmall
                )
                product.labels?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = product.price.toMonetaryString(),
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Text(text = "Error: $errorMessage", color = Color.Red)
}

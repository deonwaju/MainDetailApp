package com.deontch.feature.products.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import com.aliumujib.all.breeds.R
import com.deontch.common.design.components.ErrorScreen
import com.deontch.common.design.components.LoadingScreen
import com.deontch.core.common.util.toMonetaryString
import com.deontch.core.modules.Product
import com.deontch.feature.products.ProductsListUiState
import com.deontch.feature.products.nav.ProductsListNavigator
import com.deontch.feature.products.presentation.ProductsListViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductsScreen(
    viewModel: ProductsListViewModel = hiltViewModel(),
    navigator: ProductsListNavigator,
) {

    val uiState by viewModel.uiState.collectAsState()
    val filteredProducts by viewModel.filteredProducts.collectAsState()

    when (uiState) {
        is ProductsListUiState.Initial -> {
            Text(text = stringResource(R.string.welcome_to_the_products_screen))
        }

        is ProductsListUiState.Loading -> {
            LoadingScreen()
        }

        is ProductsListUiState.Success -> {
            ProductListContent(
                products = filteredProducts,
                onItemClick = { product ->
                    navigator.goToDetails(product.id)
                },
                onQueryChanged = { query ->
                    viewModel.onSearchQueryChanged(query)
                }
            )
        }

        is ProductsListUiState.Error -> {
            ErrorScreen(
                message = (uiState as ProductsListUiState.Error).message,
                onRetry = { viewModel.getProducts() }
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
}

@Composable
fun ProductListContent(
    products: List<Product>,
    onItemClick: (Product) -> Unit,
    onQueryChanged: (String) -> Unit,
) {
    var isListView by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        SearchField(query = searchQuery, onQueryChanged = { query ->
            searchQuery = query
            onQueryChanged(query)
        })

        SwitchViewButton(isListView = isListView, onToggle = { isListView = !isListView })

        if (products.isEmpty()) {
            NoProductsFoundMessage()
        } else {
            ProductListViewOrGridView(
                products = products,
                isListView = isListView,
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun SearchField(query: String, onQueryChanged: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = {
            Text(
                text = "Search Products By Name",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = io.eyram.iconsax.R.drawable.broken_search_normal),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        shape = CircleShape,
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun SwitchViewButton(isListView: Boolean, onToggle: () -> Unit) {
    Button(
        onClick = onToggle,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(stringResource(id = selectSwitchViewButtonTextSelector(isListView)))
    }
}

@Composable
fun NoProductsFoundMessage() {
    Text(
        text = "No products found matching your search.",
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun ProductListViewOrGridView(
    products: List<Product>,
    isListView: Boolean,
    onItemClick: (Product) -> Unit
) {
    if (isListView) {
        ProductListView(products = products, onItemClick)
    } else {
        ProductGridView(products = products, onItemClick)
    }
}

@Composable
fun ProductGridView(
    products: List<Product>,
    onItemClick: (Product) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(products) { product ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onItemClick(product) }
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
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
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
                        contentScale = ContentScale.Crop,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = product.title,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = 14.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = product.colour,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    product.labels?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.price.toMonetaryString(),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                }
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
                        .clip(RoundedCornerShape(16.dp)),
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

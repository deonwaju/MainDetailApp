package com.deontch.feature.product.details.ui

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.deontch.common.design.components.HtmlText
import com.deontch.common.design.components.LoadingScreen
import com.deontch.core.common.util.toMonetaryString
import com.deontch.core.modules.Media
import com.deontch.feature.product.details.R
import com.deontch.feature.product.details.nav.ProductDetailsNav
import com.deontch.feature.product.details.presentation.ProductDetailsUiState
import com.deontch.feature.product.details.presentation.ProductDetailsViewmodel
import com.ramcosta.composedestinations.annotation.Destination
import io.eyram.iconsax.IconSax

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
        uiState = uiState,
        onNavigateUp = navigator::onBackPressed,
    )
}

@Composable
fun ProductsDetailsScreenContent(
    uiState: ProductDetailsUiState,
    onNavigateUp: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable(onClick = onNavigateUp),
                    painter = painterResource(id = IconSax.Outline.ArrowLeft),
                    contentDescription = "Close"
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding() + 15.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                when (uiState) {
                    is ProductDetailsUiState.Idle -> {
                        item {
                            Text(text = stringResource(R.string.welcome_to_the_product_details_screen))
                        }
                    }

                    is ProductDetailsUiState.Loading -> item { LoadingScreen() }
                    is ProductDetailsUiState.Success -> {
                        uiState.data.apply {
                            item {
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .placeholder(io.eyram.iconsax.R.drawable.broken_shop)
                                        .error(io.eyram.iconsax.R.drawable.broken_icon_1)
                                        .data(featuredMedia.src)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(340.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(15)),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = price.toMonetaryString(),
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                ProductDetailRowItem(
                                    icon = painterResource(id = IconSax.Linear.Clipboard),
                                    title = stringResource(id = R.string.description),
                                    content = description,
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                ProductAttributeRowItem(
                                    icon = painterResource(id = IconSax.Linear.Colorfilter),
                                    content = stringResource(
                                        id = R.string.colors,
                                        colour
                                    ),
                                )
                            }

                            if (sizeInStock.isNotEmpty()) {
                                item {
                                    Spacer(modifier = Modifier.height(16.dp))
                                    ProductAttributeRowItem(
                                        icon = painterResource(id = IconSax.Linear.Size),
                                        content = stringResource(
                                            id = R.string.sizes_in_stock,
                                            sizeInStock.joinToString(", ")
                                        ),
                                    )
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                OtherImagesList(otherImages = media, context = context)
                                Spacer(modifier = Modifier.height(32.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OtherImagesList(otherImages: List<Media>, context: Context) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(otherImages) { media ->
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .placeholder(io.eyram.iconsax.R.drawable.broken_shop)
                    .error(io.eyram.iconsax.R.drawable.broken_icon_1)
                    .data(media.src)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun ProductDetailRowItem(
    icon: Painter,
    title: String,
    content: String,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        HtmlText(html = content)
    }
}

@Composable
private fun ProductAttributeRowItem(
    icon: Painter,
    content: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.Top) {
        Icon(painter = icon, contentDescription = null, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis
        )
    }
}


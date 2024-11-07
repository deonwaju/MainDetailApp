package com.deontch.feature.product.details

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.deontch.common.design.theme.AppTheme
import com.deontch.core.common.test.DummyData
import com.deontch.feature.product.details.presentation.ProductDetailsUiState
import com.deontch.feature.product.details.ui.ProductsDetailsScreenContent
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailsScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productDetailsScreenContent_displaysProductDetails() {
        val product = DummyData.getDummyProduct1()
        val state = ProductDetailsUiState.Success(product)

        composeTestRule.setContent {
            AppTheme {
                ProductsDetailsScreenContent(
                    uiState = state,
                    onNavigateUp = { },
                )
            }
        }

        composeTestRule.onNodeWithText(product.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(product.description).assertIsDisplayed()
        composeTestRule.onNodeWithText("$${product.price}").assertIsDisplayed() // Assuming price is formatted
    }
}
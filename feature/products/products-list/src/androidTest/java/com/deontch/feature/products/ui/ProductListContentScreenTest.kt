package com.deontch.feature.products.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.deontch.common.design.theme.AppTheme
import com.deontch.core.common.test.DummyData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductListContentScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val products = DummyData.getDummyProducts()

    @Test
    fun productList_displaysProducts_inListView() {
        composeTestRule.setContent {
            AppTheme {
                ProductListContent(
                    products = products,
                    onItemClick = { },
                    onQueryChanged = {},
                )
            }
        }

        composeTestRule.onNodeWithText("Product 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("£1200").assertIsDisplayed()
        composeTestRule.onNodeWithText("Clearance, New").assertIsDisplayed()
    }

    @Test
    fun productList_switchesToGridView() {
        composeTestRule.setContent {
            AppTheme {
                ProductListContent(
                    products = products,
                    onItemClick = {},
                    onQueryChanged = {},
                )
            }
        }

        composeTestRule.onNodeWithText("Switch to Grid View").assertIsDisplayed()
        composeTestRule.onNodeWithText("Switch to Grid View").performClick()
        composeTestRule.onNodeWithText("Switch to List View").assertIsDisplayed()
    }
}
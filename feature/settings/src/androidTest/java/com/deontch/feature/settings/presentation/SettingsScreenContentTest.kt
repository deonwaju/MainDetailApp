package com.deontch.feature.settings.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class SettingsScreenContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModel: SettingsViewModel = mock(SettingsViewModel::class.java)

    @Test
    fun testSettingsScreen_uiElementsVisibility() {
        composeTestRule.setContent {
            SettingsScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()

        composeTestRule.onNodeWithText("Change your theme").assertIsDisplayed()
        composeTestRule.onNodeWithText("Suggest or report anything").assertIsDisplayed()

        composeTestRule.onNodeWithText("Version 1.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("Made with by Aliu Mujib").assertIsDisplayed()
    }
}
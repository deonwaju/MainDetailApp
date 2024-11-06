package com.deontch.feature.settings.presentation

import androidx.compose.runtime.Immutable
import com.deontch.core.common.state.TextFieldState

@Immutable
data class SettingsUiState(
    val shouldShowThemesDialog: Boolean = false,
    val shouldShowFeedbackDialog: Boolean = false,
    val feedbackState: TextFieldState = TextFieldState(),
)

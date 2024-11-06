package com.deontch.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deontch.feature.settings.domain.usecase.SetCurrentThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val setCurrentThemeUseCase: SetCurrentThemeUseCase,
) : ViewModel() {

    private val _states = MutableStateFlow(SettingsUiState())
    val states = _states.asStateFlow()

    private val _events = MutableSharedFlow<SettingsUiEvents>()
    val events = _events

    fun setShowThemesDialogState() {
        _states.update {
            it.copy(
                shouldShowThemesDialog = !it.shouldShowThemesDialog
            )
        }
    }

    fun setShowFeedbackDialogState() {
        _states.update {
            it.copy(
                shouldShowFeedbackDialog = !it.shouldShowFeedbackDialog
            )
        }
    }


    fun setFeedbackState(
        value: String,
        error: String? = null
    ) {
        _states.update {
            it.copy(
                feedbackState = it.feedbackState.copy(
                    text = value,
                    error = error
                )
            )
        }
    }

    fun updateTheme(themeValue: Int) {
        viewModelScope.launch {
            setCurrentThemeUseCase(themeValue)
            setShowThemesDialogState()
        }
    }
}

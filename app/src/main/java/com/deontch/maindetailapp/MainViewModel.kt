package com.deontch.maindetailapp

import androidx.core.splashscreen.SplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deontch.core.preferences.domain.usecase.GetAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getAppThemeUseCase: GetAppThemeUseCase,
): ViewModel(), SplashScreen.KeepOnScreenCondition {

    private var isLoadingData: Boolean = true
    init {
        viewModelScope.launch {
            delay(500L)
            isLoadingData = false
        }
    }

    val theme = getAppThemeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0,
        )

    override fun shouldKeepOnScreen(): Boolean {
        return isLoadingData
    }
}

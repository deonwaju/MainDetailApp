package com.deontch.maindetailapp

import androidx.lifecycle.ViewModel
import com.deontch.core.preferences.domain.usecase.GetAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    getAppThemeUseCase: GetAppThemeUseCase,
): ViewModel() {
}
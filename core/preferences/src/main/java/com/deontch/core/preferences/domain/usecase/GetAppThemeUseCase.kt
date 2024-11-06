package com.deontch.core.preferences.domain.usecase

import com.deontch.core.preferences.domain.AppPreferences
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val appPreferences: AppPreferences
) {
    operator fun invoke() = appPreferences.getTheme()
}

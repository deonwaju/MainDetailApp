package com.deontch.feature.settings.domain.usecase

import com.deontch.core.preferences.domain.AppPreferences
import javax.inject.Inject

class SetCurrentThemeUseCase @Inject constructor(
    private val appPreferences: AppPreferences
) {
    suspend operator fun invoke(theme: Int) {
        appPreferences.saveTheme(theme)
    }
}

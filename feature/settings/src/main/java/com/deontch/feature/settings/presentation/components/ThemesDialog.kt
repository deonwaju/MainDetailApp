package com.deontch.feature.settings.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deontch.common.design.theme.Theme
import com.deontch.feature.settings.presentation.components.ThemeItem
import io.eyram.iconsax.IconSax

@Composable
fun ThemesDialog(onDismiss: () -> Unit, onSelectTheme: (Int) -> Unit) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = "Themes",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                ThemeItem(
                    themeName = "Use System Settings",
                    themeValue = Theme.FOLLOW_SYSTEM.themeValue,
                    icon = IconSax.Linear.Settings,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Light Mode",
                    themeValue = Theme.LIGHT_THEME.themeValue,
                    icon = IconSax.Linear.Sun,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Dark Mode",
                    themeValue = Theme.DARK_THEME.themeValue,
                    icon = IconSax.Linear.Moon,
                    onSelectTheme = onSelectTheme
                )
                ThemeItem(
                    themeName = "Material You",
                    themeValue = Theme.MATERIAL_YOU.themeValue,
                    icon = IconSax.Linear.PictureFrame,
                    onSelectTheme = onSelectTheme
                )
            }
        },
        confirmButton = {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}

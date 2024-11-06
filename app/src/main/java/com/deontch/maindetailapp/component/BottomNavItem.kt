package com.deontch.maindetailapp.component

import android.content.Context
import com.deontch.maindetailapp.R
import com.deontch.maindetailapp.navigation.NavGraphs
import com.ramcosta.composedestinations.spec.NavGraphSpec
import io.eyram.iconsax.IconSax

data class BottomNavItem(val title: String, val icon: Int, val screen: NavGraphSpec)

fun getNavItems(context: Context): List<BottomNavItem> {
    val home = BottomNavItem(
        title = context.getString(R.string.tab_title_home),
        icon = IconSax.Outline.Home,
        screen = NavGraphs.home
    )

    val settings = BottomNavItem(
        title = context.getString(R.string.tab_title_settings),
        icon = IconSax.Outline.Setting2,
        screen = NavGraphs.settings
    )

    return listOf(home, settings)
}
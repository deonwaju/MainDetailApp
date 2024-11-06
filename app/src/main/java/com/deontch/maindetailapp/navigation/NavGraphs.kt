package com.deontch.maindetailapp.navigation

import com.deontch.feature.product.details.ui.destinations.ProductsDetailsScreenDestination
import com.deontch.feature.products.ui.destinations.ProductsScreenDestination
import com.deontch.feature.settings.presentation.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {
    val home = object : NavGraphSpec {
        override val route = "home"

        override val startRoute = ProductsScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            ProductsScreenDestination, ProductsDetailsScreenDestination
        ).routedIn(this)
            .associateBy { it.route }
    }

    val settings = object : NavGraphSpec {
        override val route = "settings"

        override val startRoute = SettingsScreenDestination routedIn this

        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SettingsScreenDestination,
        ).routedIn(this)
            .associateBy { it.route }
    }

    fun root() = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            home,
            settings,
        )
    }
}
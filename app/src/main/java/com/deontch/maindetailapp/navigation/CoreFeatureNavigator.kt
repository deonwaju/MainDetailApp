package com.deontch.maindetailapp.navigation

import androidx.navigation.NavController
import com.deontch.feature.product.details.nav.ProductDetailsNav
import com.deontch.feature.product.details.ui.destinations.ProductsDetailsScreenDestination
import com.deontch.feature.products.nav.ProductsListNavigator
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CoreFeatureNavigator(
    private val navGraph: NavGraphSpec,
    private val navController: NavController,
): ProductsListNavigator, ProductDetailsNav {
    override fun goToDetails(id: Long) {
        navController.navigate(ProductsDetailsScreenDestination(productId = id) within navGraph)
    }

    override fun onBackPressed() {
        navController.navigate(navController.graph.startDestinationId)
    }
}
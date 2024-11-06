package com.deontch.maindetailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.deontch.common.design.theme.AppTheme
import com.deontch.common.design.theme.Theme
import com.deontch.feature.products.ui.destinations.ProductsScreenDestination
import com.deontch.feature.settings.presentation.destinations.SettingsScreenDestination
import com.deontch.maindetailapp.component.StandardScaffold
import com.deontch.maindetailapp.component.getNavItems
import com.deontch.maindetailapp.component.navGraph
import com.deontch.maindetailapp.navigation.CoreFeatureNavigator
import com.deontch.maindetailapp.navigation.NavGraphs
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var navigator: CoreFeatureNavigator? = null

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(viewModel)
        }
        setContent {
            val themeValue by viewModel.theme.collectAsState(
                initial = Theme.FOLLOW_SYSTEM.themeValue,
                context = Dispatchers.Main.immediate,
            )

            AppTheme(
                theme = themeValue,
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    val newBackStackEntry by navController.currentBackStackEntryAsState()
                    val route = newBackStackEntry?.destination?.route

                    StandardScaffold(
                        navController = navController,
                        items = getNavItems(context = this),
                        showBottomBar = route in listOf(
                            "home/${ProductsScreenDestination.route}",
                            "settings/${SettingsScreenDestination.route}",
                        )
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier.padding(innerPadding),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppNavigation(
                                navController = navController,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialNavigationApi::class)
    @ExperimentalAnimationApi
    @Composable
    internal fun AppNavigation(
        navController: NavHostController,
        modifier: Modifier = Modifier,
    ) {
        val navHostEngine = rememberAnimatedNavHostEngine(
            navHostContentAlignment = Alignment.TopCenter,
            rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
            defaultAnimationsForNestedNavGraph = mapOf(
                NavGraphs.home to NestedNavGraphDefaultAnimations(),
                NavGraphs.settings to NestedNavGraphDefaultAnimations(),
            )
        )

        DestinationsNavHost(
            engine = navHostEngine,
            navController = navController,
            navGraph = NavGraphs.root(),
            modifier = modifier,
            dependenciesContainerBuilder = {
                dependency(
                    currentNavigator()
                )
            }
        )
    }

    private fun DependenciesContainerBuilder<*>.currentNavigator(): CoreFeatureNavigator {
        return CoreFeatureNavigator(
            navGraph = navBackStackEntry.destination.navGraph(),
            navController = navController,
        ).also { navigator = it }
    }
}

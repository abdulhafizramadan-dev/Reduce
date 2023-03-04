package com.ahr.reduce.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.destination.addressDetailComposable
import com.ahr.reduce.navigation.destination.profileSettingsComposable
import com.ahr.reduce.ui.screen.MainScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceNavigation(
    navController: NavHostController
) {
    
    val navigator = remember(key1 = navController) {
        Navigator(navController)
    }
    
    NavHost(
        navController = navController,
        startDestination = Screen.AUTH_ROUTE.route
    ) {

        authNavigation(navigator = navigator)

        composable(route = Screen.MAIN_ROUTE.route) {
            MainScreen(
                navigator = navigator
            )
        }

        profileSettingsComposable(
            onNavigateUpClicked = { navController.navigateUp() },
            onSaveClicked = navigator.toAddressDetailScreen
        )
        addressDetailComposable(
            onNavigateUpClicked = { navController.navigateUp() },
            onSaveClicked = navigator.toHomeScreen
        )
    }
}
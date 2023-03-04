package com.ahr.reduce.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ahr.reduce.navigation.destination.*

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigator: Navigator
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route
    ) {
        homeComposable(
            modifier = modifier,
            navigateToDetailScreen = {
                navigator.toLoginScreen()
            }
        )
        marketComposable(
            modifier = modifier,
            navigateToDetailScreen = {}
        )
        transactionComposable(
            modifier = modifier,
            navigateToDetailTransactionScreen = {}
        )
        profileComposable(
            modifier = modifier,
            onLogoutClicked = {}
        )
    }
}
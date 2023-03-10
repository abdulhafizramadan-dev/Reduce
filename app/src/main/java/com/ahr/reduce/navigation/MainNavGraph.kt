package com.ahr.reduce.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahr.reduce.presentation.screen.home.HomeScreen
import com.ahr.reduce.presentation.screen.market.MarketScreen
import com.ahr.reduce.presentation.screen.profile.ProfileScreen
import com.ahr.reduce.presentation.screen.transction.TransactionScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigator: Navigator
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(
                navigator = navigator,
                modifier = modifier.fillMaxSize()
            )
        }
        composable(route = BottomBarScreen.Market.route) {
            MarketScreen(
                navigator = navigator,
                modifier = modifier.fillMaxSize()
            )
        }
        composable(route = BottomBarScreen.Transaction.route) {
            TransactionScreen(
                modifier = modifier.fillMaxSize()
            )
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(
                modifier = modifier.fillMaxSize(),
                onLogoutClickedClicked = {}
            )
        }
    }
}

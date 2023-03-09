package com.ahr.reduce.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahr.reduce.navigation.IndependentScreen.*
import com.ahr.reduce.ui.screen.MainScreen
import com.ahr.reduce.ui.screen.checkout.CheckoutScreen
import com.ahr.reduce.ui.screen.detail_address.DetailAddressScreen
import com.ahr.reduce.ui.screen.detail_product.DetailProductScreen
import com.ahr.reduce.ui.screen.profile_setting.ProfileSettingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceNavigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Graph.Auth.route
    ) {

        authNavGraph(navController = navController)

        composable(route = Graph.Main.route) {
            MainScreen()
        }

        composable(
            route = ProfileSettings.route,
            arguments = listOf(
                navArgument(ProfileSettings.PREV_SCREEN_KEY) {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument(ProfileSettings.NEXT_SCREEN_KEY) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {  navBackStackEntry ->
            val prevPage = navBackStackEntry.arguments?.getString(ProfileSettings.PREV_SCREEN_KEY)
            val nextPage = navBackStackEntry.arguments?.getString(ProfileSettings.NEXT_SCREEN_KEY)
            ProfileSettingScreen(
                navController = navController,
                prevPage = prevPage,
                nextPage = nextPage,
            )
        }
        composable(route = DetailAddress.route) {
            DetailAddressScreen(navController = navController)
        }
        composable(route = DetailProduct.route) {
            DetailProductScreen(navController = navController)
        }
        composable(route = Checkout.route) {
            CheckoutScreen(navController = navController)
        }
    }
}

sealed class Graph(val route: String) {
    object Auth : Graph("auth")
    object Main : Graph("main")
}
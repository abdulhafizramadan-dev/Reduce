package com.ahr.reduce.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahr.reduce.navigation.IndependentScreen.*
import com.ahr.reduce.presentation.screen.MainScreen
import com.ahr.reduce.presentation.screen.cart.CartScreen
import com.ahr.reduce.presentation.screen.checkout.CheckoutScreen
import com.ahr.reduce.presentation.screen.detail_address.DetailAddressScreen
import com.ahr.reduce.presentation.screen.detail_product.DetailProductScreen
import com.ahr.reduce.presentation.screen.onboarding.OnBoardingScreen
import com.ahr.reduce.presentation.screen.profile_setting.ProfileSettingScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {

    val navigator = remember(key1 = navController) {
        Navigator(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        authNavGraph(navigator = navigator)

        composable(route = Graph.Main.route) {
            MainScreen(navigator = navigator)
        }

        composable(route = OnBoarding.route) {
            OnBoardingScreen(navigator = navigator)
        }

        composable(
            route = ProfileSettings.route,
            arguments = listOf(
                navArgument(ProfileSettings.IS_REGISTER_FLOW_KEY) {
                    type = NavType.BoolType
                }
            )
        ) { navBackStackEntry ->
            val isRegisterFlow = navBackStackEntry.arguments?.getBoolean(ProfileSettings.IS_REGISTER_FLOW_KEY) ?: false
            ProfileSettingScreen(
                navigator = navigator,
                isRegisterFlow = isRegisterFlow
            )
        }
        composable(
            route = DetailAddress.route,
            arguments = listOf(
                navArgument(DetailAddress.IS_REGISTER_FLOW_KEY) {
                    type = NavType.BoolType
                }
            )
        ) { navBackStackEntry ->
            val isRegisterFlow = navBackStackEntry.arguments?.getBoolean(ProfileSettings.IS_REGISTER_FLOW_KEY) ?: false
            DetailAddressScreen(
                navigator = navigator,
                isRegisterFlow = isRegisterFlow
            )
        }
        composable(
            route = DetailProduct.route,
            arguments = listOf(
                navArgument(DetailProduct.PRODUCT_ID_KEY) {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            val productId = navBackStackEntry.arguments?.getInt(DetailProduct.PRODUCT_ID_KEY) ?: 1
            DetailProductScreen(
                navController = navController,
                navigator  = navigator,
                productId = productId
            )
        }
        composable(route = Cart.route) {
            CartScreen(navigator = navigator)
        }
        composable(route = Checkout.route) {
            CheckoutScreen(navigator = navigator)
        }
    }
}

sealed class Graph(val route: String) {
    object Auth : Graph("auth_graph")
    object Main : Graph("main_graph")
}
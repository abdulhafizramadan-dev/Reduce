package com.ahr.reduce.navigation

import androidx.navigation.NavHostController

class Navigator(
    private val navController: NavHostController
) {

    val toLoginScreen: () -> Unit = {
        navController.navigate(Screen.LOGIN.route) {
            popUpTo(Screen.LOGIN.route) { inclusive = true }
        }
    }

    val toRegisterScreen: () -> Unit = {
        navController.navigate(Screen.REGISTER.route)
    }

    val toProfileSettingsScreen: () -> Unit = {
        navController.navigate(Screen.PROFILE_SETTINGS.route)
    }

    val toAddressDetailScreen: () -> Unit = {
        navController.navigate(Screen.DETAIL_ADDRESS.route)
    }

    val toDetailProductScreen: (Int) -> Unit = { productId ->
        navController.navigate("detailProduct/$productId")
    }

    val toCheckoutScreen: () -> Unit = {
        navController.navigate(Screen.CHECKOUT.route)
    }


    // Bottom Navigation
    val toHomeScreen: () -> Unit = {
        navController.navigate(Screen.MAIN_ROUTE.route) {
            popUpTo(Screen.HOME.route) { inclusive = true }
        }
    }

}
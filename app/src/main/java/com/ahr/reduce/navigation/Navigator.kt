package com.ahr.reduce.navigation

import androidx.navigation.NavHostController
import com.ahr.reduce.navigation.AuthScreen.Register
import com.ahr.reduce.navigation.IndependentScreen.DetailAddress
import com.ahr.reduce.navigation.IndependentScreen.ProfileSettings

class Navigator(private val navController: NavHostController) {

    val navigateToAuthGraph: (route: String) -> Unit = { route ->
        navController.navigate(Graph.Auth.route) {
            popUpTo(route = route) {
                inclusive = true
            }
        }
    }

    val navigateToMainGraph: (route: String) -> Unit = { route ->
        navController.navigate(Graph.Main.route) {
            popUpTo(route = route) {
                inclusive = true
            }
        }
    }

    val navigateToRegisterScreen: () -> Unit = {
        navController.navigate(Register.route)
    }

    val navigateToProfileSettings: () -> Unit = {
        navController.navigate(ProfileSettings.route)
    }

    val navigateToDetailAddress: () -> Unit = {
        navController.navigate(DetailAddress.route)
    }

    val navigateToDetailProduct: (
        productId: Int
    ) -> Unit = { productId ->
        val route = IndependentScreen.DetailProduct.getRoute(productId = productId)
        navController.navigate(route)
    }

    val navigateToCheckout: () -> Unit = {
        navController.navigate(IndependentScreen.Checkout.route)
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    // Registration Flow
    val navigateToProfileSettingsRegisterFlow: () -> Unit = {
        navController.navigate(ProfileSettings.getRoute(true)) {
            popUpTo(Register.route) {
                inclusive = true
            }
        }
    }

    val navigateToDetailAddressRegisterFlow: () -> Unit = {
        navController.navigate(DetailAddress.getRoute(true))
    }

}
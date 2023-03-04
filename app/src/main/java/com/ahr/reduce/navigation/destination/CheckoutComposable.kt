package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.checkout.CheckoutScreen

fun NavGraphBuilder.checkoutComposable(
    onNavigateUpClicked: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    composable(route = Screen.CHECKOUT.route) {
        CheckoutScreen(
            onNavigateUpClicked = onNavigateUpClicked,
            navigateToHomeScreen = navigateToHomeScreen,
            modifier = Modifier.fillMaxSize()
        )
    }
}
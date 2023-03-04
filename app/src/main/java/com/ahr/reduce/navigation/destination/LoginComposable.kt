package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.login.LoginScreen

fun NavGraphBuilder.loginComposable(
    navigateToHomeScreen: () -> Unit,
    navigateToRegisterScreen: () -> Unit,
) {
    composable(route = Screen.LOGIN.route) {
        LoginScreen(
            navigateToHomeScreen = navigateToHomeScreen,
            navigateToRegisterScreen = navigateToRegisterScreen,
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.register.RegisterScreen

fun NavGraphBuilder.registerComposable(
    navigateToProfileSettingsScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    composable(route = Screen.REGISTER.route) {
        RegisterScreen(
            navigateToProfileSettingsScreen = navigateToProfileSettingsScreen,
            navigateToLoginScreen = navigateToLoginScreen,
            modifier = Modifier.fillMaxSize()
        )
    }
}
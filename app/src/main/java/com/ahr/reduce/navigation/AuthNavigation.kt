package com.ahr.reduce.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ahr.reduce.navigation.destination.loginComposable
import com.ahr.reduce.navigation.destination.registerComposable

fun NavGraphBuilder.authNavigation(
    navigator: Navigator,
) {
    navigation(
        startDestination = Screen.LOGIN.route,
        route = Screen.AUTH_ROUTE.route
    ) {
        loginComposable(
            navigateToHomeScreen = navigator.toHomeScreen,
            navigateToRegisterScreen = navigator.toRegisterScreen
        )
        registerComposable(
            navigateToLoginScreen = navigator.toLoginScreen,
            navigateToProfileSettingsScreen = navigator.toProfileSettingsScreen
        )
    }
}
package com.ahr.reduce.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ahr.reduce.ui.screen.login.LoginScreen
import com.ahr.reduce.ui.screen.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(
    navigator: Navigator
) {
    navigation(
        startDestination = AuthScreen.Login.route,
        route = Graph.Auth.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(
                navigator = navigator,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = AuthScreen.Register.route) {
            RegisterScreen(
                navigator = navigator,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
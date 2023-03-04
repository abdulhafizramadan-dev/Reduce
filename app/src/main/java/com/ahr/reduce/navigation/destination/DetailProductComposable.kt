package com.ahr.reduce.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen

fun NavGraphBuilder.detailComposable(
    navigateToHomeScreen: () -> Unit
) {
    composable(route = Screen.LOGIN.route) {
    }
}
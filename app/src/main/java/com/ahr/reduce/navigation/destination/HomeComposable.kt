package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeComposable(
    navigateToDetailScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(route = Screen.HOME.route) {
        HomeScreen(
            navigateToDetailScreen = navigateToDetailScreen,
            modifier = modifier.fillMaxSize()
        )
    }
}
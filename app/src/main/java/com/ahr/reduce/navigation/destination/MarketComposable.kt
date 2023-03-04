package com.ahr.reduce.navigation.destination

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen

fun NavGraphBuilder.marketComposable(
    navigateToDetailScreen: (Int) -> Unit,
    modifier: Modifier
) {
    composable(route = Screen.MARKET.route) {
//        HomeScreen(
//            navigateToDetailScreen = navigateToDetailScreen,
//            modifier = Modifier.fillMaxSize()
//        )
    }
}
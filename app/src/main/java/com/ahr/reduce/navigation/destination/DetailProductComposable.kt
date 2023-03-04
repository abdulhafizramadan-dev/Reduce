package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.detail_product.DetailProductScreen

fun NavGraphBuilder.detailComposable(
    onNavigateUpClicked: () -> Unit,
    navigateToCheckoutScreen: () -> Unit,
) {
    composable(route = Screen.DETAIL_PRODUCT.route) {
        DetailProductScreen(
            onNavigateUpClicked = onNavigateUpClicked,
            navigateToCheckoutScreen = navigateToCheckoutScreen,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.transction.TransactionScreen

fun NavGraphBuilder.transactionComposable(
    navigateToDetailTransactionScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(route = Screen.TRANSACTION.route) {
        TransactionScreen(
            navigateToDetailTransactionScreen = navigateToDetailTransactionScreen,
            modifier = modifier.fillMaxSize()
        )
    }
}
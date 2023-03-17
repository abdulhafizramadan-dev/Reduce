package com.ahr.reduce.presentation.screen.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navigator: Navigator,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.cart,
                onNavigationClicked = navigator.navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->
        CartContent(
            items = emptyList(),
            modifier = Modifier.padding(paddingValues),
            onCartItemClicked = navigator.navigateToDetailProduct,
            onButtonClicked = navigator.navigateToCheckout
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        CartScreen(navigator = Navigator(navController))
    }
}
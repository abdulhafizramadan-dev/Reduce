package com.ahr.reduce.ui.screen.checkout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.checkout,
                onNavigationClicked = {
                    navController.navigateUp()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = modifier
    ) { paddingValues ->
        CheckoutContent(
            address = "Jl. Mawar Blok A12 No.99, Kel. Cinta, Kec. Curug, Kab. Tangerang, Jawa Barat 1189.",
            modifier = Modifier.padding(paddingValues),
            snackbarHostState = snackbarHostState,
            scope = scope,
            onCheckoutClicked = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutScreen() {
    ReduceTheme {
        CheckoutScreen(
            navController = rememberNavController()
        )
    }
}
package com.ahr.reduce.ui.screen.checkout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.checkout,
                onNavigationClicked = { }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        CheckoutContent(
            address = "Jl. Mawar Blok A12 No.99, Kel. Cinta, Kec. Curug, Kab. Tangerang, Jawa Barat 1189.",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutScreen() {
    ReduceTheme {
        CheckoutScreen()
    }
}
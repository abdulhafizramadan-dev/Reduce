package com.ahr.reduce.presentation.screen.cart

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.detail_product,
                onNavigationClicked = { }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        CartContent(modifier = Modifier.padding(paddingValues))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    ReduceTheme {
        CartScreen()
    }
}
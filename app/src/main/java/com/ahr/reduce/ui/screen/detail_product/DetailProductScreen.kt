package com.ahr.reduce.ui.screen.detail_product

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.topappbar.DetailTopAppBar
import com.ahr.reduce.data.products
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(
    modifier: Modifier = Modifier
) {

    var orderCount by remember { mutableStateOf(1) }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.detail_product,
                onNavigationClicked = { }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        DetailProductContent(
            product = products[0],
            onCartClicked = { },
            modifier = Modifier.padding(paddingValues),
            orderCount = orderCount,
            onOrderCountChanged = { orderCount = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    ReduceTheme {
        DetailProductScreen()
    }
}
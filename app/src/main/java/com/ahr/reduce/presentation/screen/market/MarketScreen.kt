package com.ahr.reduce.presentation.screen.market

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.data.products
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.product.ProductItem
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun MarketScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {

    var searchQuery by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(152.dp),
        contentPadding= PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                MarketTopAppBar(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it },
                    onCartClicked = { },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                MarketFilter(onFilterChipClicked = {}, onSortClicked = {})
            }
        }

        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                id = product.id,
                type = product.type,
                name = product.name,
                photo = product.photo,
                onProductClicked = {
                    navigator.navigateToDetailProduct(product.id)
                },
                smallItem = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarketScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        MarketScreen(navigator = Navigator(navController))
    }
}
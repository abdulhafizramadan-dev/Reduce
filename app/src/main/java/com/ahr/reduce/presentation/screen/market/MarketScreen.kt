package com.ahr.reduce.presentation.screen.market

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.product.ProductItem
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun MarketScreen(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    marketViewModel: MarketViewModel = hiltViewModel(),
) {

    val marketUiState by marketViewModel.marketUiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(152.dp),
        contentPadding= PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                MarketTopAppBar(
                    searchQuery = searchQuery,
                    onSearchQueryChanged = { searchQuery = it },
                    onCartClicked = navigator.navigateToCart,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                MarketFilter(onFilterChipClicked = {}, onSortClicked = {})
            }
        }

        if (marketUiState is UiState.Success) {
            items(
                items = (marketUiState as UiState.Success<List<Product>>).data,
                key = { it.id }
            ) { product ->
                ProductItem(
                    documentId = product.documentId.toString(),
                    type = product.type,
                    name = product.name,
                    photo = product.photo,
                    onProductClicked = {
                        navigator.navigateToDetailProduct(product.documentId.toString())
                    },
                    smallItem = false
                )
            }
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
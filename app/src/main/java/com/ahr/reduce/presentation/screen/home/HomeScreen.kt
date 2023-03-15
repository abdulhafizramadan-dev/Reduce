package com.ahr.reduce.presentation.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.domain.data.products
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.product.ProductItem
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    var searchQuery by remember { mutableStateOf("") }

    val navigateToDetailProduct: (Int) -> Unit = { productId ->
        navigator.navigateToDetailProduct(productId)
    }


    val homeProducts by homeViewModel.homeProducts.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getHomeProduct()
    }

    LaunchedEffect(key1 = homeProducts) {
        Log.d("TAG", "HomeScreen: homeProducts = $homeProducts")
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(152.dp),
        contentPadding= PaddingValues(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            HomeTopAppBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                onCartClicked = navigator.navigateToCart,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            HomeBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            )
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
                onProductClicked = navigateToDetailProduct,
                smallItem = false
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        HomeScreen(navigator = Navigator(navController))
    }
}
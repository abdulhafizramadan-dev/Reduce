package com.ahr.reduce.presentation.screen.detail_product

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProductScreen(
    documentId: String,
    navigator: Navigator,
    modifier: Modifier = Modifier,
    detailProductViewModel: DetailProductViewModel = hiltViewModel()

) {

    var orderCount by remember { mutableStateOf(1) }

    LaunchedEffect(key1 = Unit) {
        detailProductViewModel.getDetailProduct(documentId)
    }

    val detailProductUiState by detailProductViewModel.detailProductUiState.collectAsState()

    LaunchedEffect(key1 = detailProductUiState) {
        Log.d("TAG", "DetailProductScreen: documentId=$documentId")
        when (detailProductUiState) {
            is UiState.Idle -> {}
            is UiState.Loading -> {
                Log.d("TAG", "DetailProductScreen: Loading...")
            }
            is UiState.Success -> {
                Log.d("TAG", "DetailProductScreen: Success = ${(detailProductUiState as UiState.Success<Product>).data}")
            }
            is UiState.Error -> {
                Log.d("TAG", "DetailProductScreen: Error = ${(detailProductUiState as UiState.Error).exception.message}")
            }
        }

    }

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.detail_product,
                onNavigationClicked = {
                    navigator.navigateUp()
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        if (detailProductUiState is UiState.Success) {
            DetailProductContent(
                product = (detailProductUiState as UiState.Success<Product>).data,
                onCartClicked = navigator.navigateToCheckout,
                modifier = Modifier.padding(paddingValues),
                orderCount = orderCount,
                onOrderCountChanged = { orderCount = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    ReduceTheme {
//        DetailProductScreen(
//            productId = 0,
//            navController = navController,
//            navigator = Navigator(navController)
//        )
    }
}
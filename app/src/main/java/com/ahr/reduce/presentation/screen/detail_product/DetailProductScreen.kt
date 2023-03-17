package com.ahr.reduce.presentation.screen.detail_product

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

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
    val addToCartState by detailProductViewModel.addToCartUiState.collectAsState()

    val messageBarState = rememberMessageBarState()

    LaunchedEffect(key1 = detailProductUiState) {
        when (detailProductUiState) {
            is UiState.Idle -> {}
            is UiState.Loading -> {
                Log.d("TAG", "DetailProductScreen: Loading...")
            }
            is UiState.Success -> {
                Log.d(
                    "TAG",
                    "DetailProductScreen: Success = ${(detailProductUiState as UiState.Success<Product>).data}"
                )
            }
            is UiState.Error -> {
                Log.d(
                    "TAG",
                    "DetailProductScreen: Error = ${(detailProductUiState as UiState.Error).exception.message}"
                )
            }
        }

    }

    LaunchedEffect(key1 = addToCartState) {
        when (addToCartState) {
            is UiState.Idle, is UiState.Loading -> {}
            is UiState.Success -> {
                messageBarState.addSuccess("Product berhasil di masukan ke dalam keranjang!")
            }
            is UiState.Error -> {
                messageBarState.addError(NetworkErrorException("Product berhasil di masukan ke dalam keranjang!"))
                Log.d(
                    "TAG",
                    "DetailProductScreen: Error = ${(addToCartState as UiState.Error).exception.message}"
                )
            }
        }

    }

    ContentWithMessageBar(messageBarState = messageBarState) {
        Scaffold(
            topBar = {
                DetailTopAppBar(
                    title = R.string.detail_product,
                    onNavigationClicked = {
                        navigator.navigateUp()
                    },
                    actions = {
                        IconButton(onClick = { detailProductViewModel.addToCart(documentId) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_cart),
                                contentDescription = stringResource(id = R.string.add_to_cart),
                            )
                        }
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
                    onOrderCountChanged = { orderCount = it },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        DetailProductScreen(
            documentId = "",
            navigator = Navigator(navController)
        )
    }
}
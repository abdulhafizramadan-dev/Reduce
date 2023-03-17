package com.ahr.reduce.presentation.screen.checkout

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.data.dummyProduct
import com.ahr.reduce.navigation.Graph
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    checkoutViewModel: CheckoutViewModel = hiltViewModel(),
    productDocumentId: String,
    navigator: Navigator
) {

    val messageBarState = rememberMessageBarState()
    val userAddress = checkoutViewModel.userAddressState
    val productsUiState by checkoutViewModel.detailProductUiState.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        checkoutViewModel.getDetailProduct(documentId = productDocumentId)
    }

    LaunchedEffect(key1 = productsUiState) {
        when (productsUiState) {
            is UiState.Idle, is UiState.Loading -> {}
            is UiState.Success -> {
            }
            is UiState.Error -> {
                messageBarState.addError(NetworkErrorException("Product berhasil di masukan ke dalam keranjang!"))
                Log.d("TAG", "DetailProductScreen: Error = ${(productsUiState as UiState.Error).exception.message}")
            }
        }

    }

    ContentWithMessageBar(messageBarState = messageBarState) {
        Scaffold(
            topBar = {
                DetailTopAppBar(
                    title = R.string.checkout,
                    onNavigationClicked = navigator.navigateUp
                )
            },
            modifier = modifier
        ) { paddingValues ->
            CheckoutContent(
                address = userAddress,
                modifier = Modifier.padding(paddingValues),
                products = listOf(dummyProduct),
                onCheckoutClicked = {
                    scope.launch {
                        messageBarState.addSuccess("Checkout produk berhasil dilakukan!")
                        delay(3000L)
                        navigator.navigateToMainGraph(Graph.Main.route)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        CheckoutScreen(
            navigator = Navigator(navController),
            productDocumentId = ""
        )
    }
}
package com.ahr.reduce.presentation.screen.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.data.Product
import com.ahr.reduce.data.products
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CheckoutContent(
    address: String,
    onCheckoutClicked: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    val scrollState = rememberScrollState()

    Column {
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            CheckoutHeader(address = address)
            Divider(modifier = Modifier.padding(top = 12.dp))
            CheckProductList(
                products = listOf(products[0]),
                modifier = Modifier.padding(top = 16.dp)
            )
            Divider()
            CheckoutPaymentSummary(
                total = "Rp. 15.000",
                shippingCost = "Rp. 10.000"
            )
        }

        CheckoutButton(
            total = "Rp. 15.000",
            onButtonClicked = onCheckoutClicked,
            snackbarHostState = snackbarHostState,
            scope = scope
        )
    }
}

@Composable
fun CheckProductList(
    products: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        products.forEach { product ->
            CheckoutProductItem(
                product = product,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
        }
        Divider(Modifier.padding(horizontal = 16.dp), thickness = .5.dp)
        CheckoutLabelAndPrice(
            label = stringResource(R.string.subtotal),
            price = "Rp. 15.000",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun CheckoutPaymentSummary(
    total: String,
    shippingCost: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .padding(bottom = 32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.summary_payment),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        CheckoutLabelAndPrice(label = stringResource(id = R.string.subtotal), price = total)
        CheckoutLabelAndPrice(label = stringResource(id = R.string.shipping_cost), price = shippingCost)
    }
}

@Composable
fun CheckoutLabelAndPrice(
    label: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = price,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun CheckoutButton(
    total: String,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    Row(
        modifier = modifier
            .shadow(
                elevation = 1.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .padding(bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.subtotal),
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 26.sp,
                    letterSpacing = (-.5).sp
                )
            )
            Text(
                text = total,
                style = MaterialTheme.typography.bodyLarge.copy(
                    lineHeight = 26.sp,
                    letterSpacing = (-.5).sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        ReduceFilledButton(
            title = R.string.checkout,
            onButtonClicked = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Pesanan berhasil dibuat!"
                    )
                    delay(100)
                    onButtonClicked()
                }
            }
        )
    }
}
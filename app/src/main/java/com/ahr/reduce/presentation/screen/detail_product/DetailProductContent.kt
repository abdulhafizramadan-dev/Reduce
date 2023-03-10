package com.ahr.reduce.presentation.screen.detail_product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailProductContent(
    product: Product,
    onCartClicked: () -> Unit,
    orderCount: Int,
    onOrderCountChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(id = product.photo),
                contentDescription = product.name,
                modifier = Modifier
                    .height(290.dp)
                    .fillMaxWidth()
            )

            DetailProductHeader(
                product = product,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )

            Divider(color = Color(0xFFADADAD))

            DetailProductOrder(
                orderCount = orderCount,
                onOrderCountChanged = onOrderCountChanged,
                modifier = Modifier.fillMaxWidth()
            )
        }

        DetailProductButtonCart(onCartClicked = onCartClicked)
    }
}

@Composable
fun DetailProductHeader(
    product: Product,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(all = 16.dp)) {
        Text(
            text = product.price,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 23.sp
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Tukar Wadah Home Care",
            style = MaterialTheme.typography.titleSmall.copy(
                letterSpacing = 0.1.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleSmall.copy(
                letterSpacing = 0.1.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalLayoutApi
@Composable
fun DetailProductOrder(
    orderCount: Int,
    onOrderCountChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val filterOrders = listOf("Atur Sesukamu", "1 Pcs")
    var selectedFilterOrder by  remember { mutableStateOf(filterOrders[0]) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.order_count_format, orderCount.toString()),
            style = MaterialTheme.typography.titleSmall.copy(
                letterSpacing = 0.1.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filterOrders.forEach { type ->
                FilterChip(
                    selected = selectedFilterOrder == type,
                    onClick = { 
                        selectedFilterOrder = type
                        if (selectedFilterOrder == filterOrders[1]) {
                            onOrderCountChanged(1)
                        }
                    },
                    label = { Text(text = type, style = MaterialTheme.typography.titleSmall) }
                )
            }
        }
        if (selectedFilterOrder == filterOrders[0]) {
            Spacer(modifier = Modifier.padding(top = 26.dp))
            ReduceOutlinedTextField(
                label = R.string.label_order_count,
                text = orderCount.toString(),
                onTextChanged = { onOrderCountChanged(it.toIntOrNull() ?: 0) },
                largeGapLabel = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Composable
fun DetailProductButtonCart(
    onCartClicked: () -> Unit,
    modifier: Modifier =Modifier
) {
    Box(
        modifier = modifier
            .height(84.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .shadow(
                1.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f)
            ),
    ) {
        ReduceFilledButton(
            title = R.string.order_now,
            onButtonClicked = onCartClicked,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }

}
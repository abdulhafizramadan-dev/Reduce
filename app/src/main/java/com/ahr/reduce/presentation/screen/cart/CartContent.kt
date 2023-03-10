package com.ahr.reduce.presentation.screen.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.presentation.component.product.ProductItem

@Composable
fun CartContent(
    items: List<Product>,
    onCartItemClicked: (productId: Int) -> Unit,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(all = 16.dp),
            modifier = Modifier
                .weight(1f)
        ) {
            items(items = items, key = { it.id }) { product ->
                CartItem(
                    product = product,
                    onCartItemClicked = onCartItemClicked
                )
            }
        }
        CartProductCheckoutButton(
            "Rp. 15.ooo",
            count = 1,
            onButtonClicked = onButtonClicked,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CartItem(
    product: Product,
    onCartItemClicked: (productId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { onCartItemClicked(product.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductItem(
            id = product.id,
            type = product.type,
            name = product.name,
            photo = product.photo,
            onProductClicked = {},
            smallItem = true,
            modifier = Modifier.weight(2f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(4f)
        ) {
            CartProductTitle(title = product.name)
            Spacer(modifier = Modifier.height(8.dp))
            CartProductPrice(price = product.price)
        }
    }
}

@Composable
fun CartProductTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        color = MaterialTheme.colorScheme.onBackground,
        lineHeight = 20.sp,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
    )
}

@Composable
fun CartProductPrice(
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = price,
        color = MaterialTheme.colorScheme.onBackground,
        lineHeight = 20.sp,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.1.sp
        ),
        modifier = modifier
    )
}

@Composable
fun CartProductCheckoutButton(
    total: String,
    count: Int,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
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
        Button(
            onClick = onButtonClicked,
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.order, count),
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Filled.ArrowRightAlt,
                contentDescription = null
            )
        }
    }
}
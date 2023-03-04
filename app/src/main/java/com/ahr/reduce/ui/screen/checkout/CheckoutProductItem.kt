package com.ahr.reduce.ui.screen.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.data.Product
import com.ahr.reduce.data.products
import com.ahr.reduce.ui.component.product.ProductItem
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun CheckoutProductItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProductItem(
            id = product.id,
            type = product.type,
            name = product.name,
            photo = product.photo,
            onProductClicked = {},
            smallItem = true,
            modifier = Modifier.weight(1.32f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    letterSpacing = 0.1.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Ukuran : 1 Pcs",
                style = MaterialTheme.typography.bodyMedium.copy(
                    letterSpacing = 0.1.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCheckoutProductItem() {
    ReduceTheme {
        CheckoutProductItem(product = products[0], modifier = Modifier.fillMaxWidth())
    }
}
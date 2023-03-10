package com.ahr.reduce.presentation.screen.transction

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.domain.data.Product
import com.ahr.reduce.domain.data.products
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun TransactionProsesItem(
    product: Product,
    onTransactionClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable { onTransactionClicked(product.id) }
                .padding(all = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.photo),
                contentDescription = null,
                modifier = Modifier.height(82.dp)
            )
            Spacer(modifier = Modifier.width(22.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${product.name} (Isi Ulang)",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Ukuran : 1 Pcs",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Rp. 15.ooo",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
        Divider(thickness = 0.5.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransactionProsesItem() {
    ReduceTheme {
        TransactionProsesItem(
            product = products[0],
            onTransactionClicked = {}
        )
    }
}
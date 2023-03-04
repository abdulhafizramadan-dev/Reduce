package com.ahr.reduce.ui.screen.transction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.data.Product

@Composable
fun TransactionContent(
    transactions: List<Product>,
    modifier: Modifier = Modifier,
) {
    if (transactions.isNotEmpty()) {
        TransactionList(transactions = transactions, modifier = modifier)
    } else {
        EmptyTransaction(modifier = modifier)
    }
}

@Composable
fun TransactionList(
    transactions: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        transactions.forEach { product ->
            TransactionProsesItem(
                product = product,
                onTransactionClicked = {}
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "1 Produk",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            )
            Text(
                text = "Total Pesanan : Rp. 17.ooo",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
        Divider(thickness = 0.5.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalShipping,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pesanan sedang dalam proses ",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Divider(thickness = 0.5.dp)
    }
}

@Composable
fun EmptyTransaction(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_no_transaction),
            contentDescription = null,
            modifier = Modifier
                .height(201.dp)
                .width(221.dp)
        )
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = stringResource(R.string.empty_transaction_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.empty_transaction_description),
            style = MaterialTheme.typography.bodyMedium.copy(
                lineHeight = 24.sp,
                letterSpacing = .5.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.7f
                ),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
    }
}
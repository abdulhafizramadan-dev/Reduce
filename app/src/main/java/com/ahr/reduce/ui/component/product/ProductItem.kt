package com.ahr.reduce.ui.component.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    type: String,
    name: String,
    photo: Int,
    onProductClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        onClick = onProductClicked,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                top = 10.dp,
                end = 20.dp,
                bottom = 25.dp
            )
        ) {
            Image(
                painter = painterResource(id = photo),
                contentDescription = null,
                modifier = Modifier
                    .height(114.dp)
                    .fillMaxWidth()
            )
            Divider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = type,
                style = MaterialTheme.typography.labelSmall.copy(
                    lineHeight = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(
                        alpha = 0.8f
                    ),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 21.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductItem() {
    ReduceTheme {
        ProductItem(
            type = "Minyak Goreng",
            name = "Bimoli",
            photo = R.drawable.bimoli,
            onProductClicked = { }
        )
    }
}
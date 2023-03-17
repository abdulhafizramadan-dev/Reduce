package com.ahr.reduce.presentation.component.product

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    id: Int,
    type: String,
    name: String,
    photo: String,
    onProductClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    smallItem: Boolean = false,
) {
    OutlinedCard(
        shape = MaterialTheme.shapes.medium,
        onClick = { onProductClicked(id) },
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    ) {
        val paddingStart = if (smallItem) 16 else 20
        val paddingTop = if (smallItem) 6 else 10
        val paddingEnd = if (smallItem) 16 else 20
        val paddingBottom = if (smallItem) 12 else 20
        Column(
            modifier = Modifier.padding(
                start = paddingStart.dp,
                top = paddingTop.dp,
                end = paddingEnd.dp,
                bottom = paddingBottom.dp
            )
        ) {
            val imageHeight = if (smallItem) 90 else 124
            AsyncImage(
                model = photo,
                contentDescription = null,
                modifier = Modifier
                    .height(imageHeight.dp)
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
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            val productNameTextStyle = if (smallItem) MaterialTheme.typography.bodySmall else MaterialTheme.typography.bodyLarge
            Text(
                text = name,
                style = productNameTextStyle.copy(
                    lineHeight = 21.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProductItem() {
    ReduceTheme {
        ProductItem(
            id = 1,
            type = "Minyak Goreng",
            name = "Bimoli",
            photo = "",
            onProductClicked = { }
        )
    }
}
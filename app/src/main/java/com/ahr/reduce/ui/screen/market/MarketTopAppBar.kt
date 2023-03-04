package com.ahr.reduce.ui.screen.market

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.textfield.ReduceSearchOutlinedTextField
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun MarketTopAppBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onCartClicked: ()-> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReduceSearchOutlinedTextField(
            query = searchQuery,
            onQueryChanged = onSearchQueryChanged,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = onCartClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = stringResource(R.string.to_cart_screen),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMarketTopAppBar() {
    ReduceTheme {
        MarketTopAppBar(
            searchQuery = "",
            onSearchQueryChanged = {},
            onCartClicked = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
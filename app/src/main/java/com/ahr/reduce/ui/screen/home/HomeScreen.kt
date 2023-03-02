package com.ahr.reduce.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.ui.component.product.ProductItem
import com.ahr.reduce.ui.component.textfield.ReduceSearchOutlinedTextField
import com.ahr.reduce.ui.data.products
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    var searchQuery by remember { mutableStateOf("") }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(152.dp),
        contentPadding= PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            ReduceSearchOutlinedTextField(
                query = searchQuery,
                onQueryChanged = { searchQuery = it },
                modifier = Modifier.padding(top = 24.dp)
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            HomeBanner(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            )
        }

        items(
            items = products,
            key = { it.id }
        ) { product ->
            ProductItem(
                type = product.type,
                name = product.name,
                photo = product.photo,
                onProductClicked = { }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    ReduceTheme {
        HomeScreen()
    }
}
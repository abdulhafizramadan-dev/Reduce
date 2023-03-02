package com.ahr.reduce.ui.screen.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.ui.component.text.TextWithLocation
import com.ahr.reduce.ui.component.textfield.ReduceSearchOutlinedTextField
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        ReduceSearchOutlinedTextField(
            query = searchQuery,
            onQueryChanged = { searchQuery = it },
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 14.dp)
        )

        TextWithLocation(
            location = "Curug, Kabupaten Tanggerang",
            modifier = Modifier
                .padding(top = 26.dp)
                .padding(horizontal = 10.dp)
        )

        HomeGreetingText(
            name = "Abdul",
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun HomeGreetingText(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Hi, $name",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Kamu belum pernah belanja minim sampah di reduce",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 13.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    ReduceTheme {
        HomeScreen()
    }
}
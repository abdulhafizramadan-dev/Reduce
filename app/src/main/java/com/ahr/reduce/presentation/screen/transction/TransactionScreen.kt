package com.ahr.reduce.presentation.screen.transction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.domain.data.dummyProduct
import com.ahr.reduce.presentation.component.textfield.ReduceSearchOutlinedTextField
import com.ahr.reduce.ui.theme.ReduceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }
    val tabsList = listOf(
        "Diproses",
        "Dikirim",
        "Dibatalkan",
        "Selesai",
    )

    Scaffold(
        topBar = {
            TransactionTopAppBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                pagerState = pagerState,
                tabsList = tabsList,
                scope = scope,
                modifier = Modifier.fillMaxWidth()
            )
        },
        modifier = modifier
    ) { paddingValues ->
        HorizontalPager(
            pageCount = tabsList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) { pageIndex ->
            when (pageIndex) {
                0 -> {
                    TransactionContent(
                        transactions = listOf(dummyProduct),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                else -> {
                    TransactionContent(
                        transactions = emptyList(),
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionTopAppBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    tabsList: List<String>,
    scope: CoroutineScope
) {
    Column(modifier = modifier) {
        ReduceSearchOutlinedTextField(
            query = searchQuery,
            onQueryChanged = onSearchQueryChanged,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TransactionTabBar(
            pagerState = pagerState,
            scope = scope,
            tabsList = tabsList,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransactionTabBar(
    pagerState: PagerState,
    scope: CoroutineScope,
    tabsList: List<String>,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        edgePadding = 16.dp
    ) {
        tabsList.forEachIndexed { index, tab ->
            val isSelected = pagerState.currentPage == index
            Tab(
                selected = isSelected,
                text = {
                    val color = if (isSelected)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onBackground
                    Text(text = tab, color = color)
                },
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewTransactionScreen() {
    ReduceTheme {
        TransactionScreen(
            modifier = Modifier.fillMaxSize(),
        )
    }
}
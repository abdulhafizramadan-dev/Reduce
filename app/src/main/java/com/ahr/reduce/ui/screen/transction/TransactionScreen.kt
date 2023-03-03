package com.ahr.reduce.ui.screen.transction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.ui.component.textfield.ReduceSearchOutlinedTextField
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
        "Selesai",
        "Dibatalkan"
    )

    Scaffold(
        topBar = {
            TransactionTopAppBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { searchQuery = it },
                pagerState = pagerState,
                tabsList = tabsList,
                scope = scope
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
        ) {
            TransactionContent(
                transactions = emptyList(),
                modifier = Modifier.fillMaxSize()
            )
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
            tabsList = tabsList
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
        edgePadding = 0.dp
    ) {
        tabsList.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                text = { Text(text = tab) },
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
            modifier = Modifier.fillMaxSize()
        )
    }
}
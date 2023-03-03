package com.ahr.reduce.ui.screen.transction

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.ui.theme.ReduceTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState(3)
    val scope = rememberCoroutineScope()
    var searchQuery by remember { mutableStateOf("") }
    val tabsList = listOf(
        "Diproses",
        "Menunggu Pembayaran (0)",
        "Selesai"
    )

//    Scaffold(
//        topBar = {
//            TransactionTopAppBar(
//                searchQuery = searchQuery,
//                onSearchQueryChanged = { searchQuery = it },
//                pagerState = pagerState,
//                tabsList = tabsList,
//                scope = scope
//            )
//        }
//    ) {
//
//    }
//    TransactionTopAppBar(
//        searchQuery = searchQuery,
//        onSearchQueryChanged = { searchQuery = it },
//        pagerState = pagerState,
//        tabsList = tabsList,
//        scope = scope
//    )

    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
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

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun TransactionTopAppBar(
//    searchQuery: String,
//    onSearchQueryChanged: (String) -> Unit,
//    pagerState: PagerState,
//    modifier: Modifier = Modifier,
//    tabsList: List<String>,
//    scope: CoroutineScope
//) {
//    Column {
//        TopAppBar(
//            backgroundColor = MaterialTheme.colorScheme.background,
//            modifier = modifier
//        ) {
//            Column {
//                ReduceSearchOutlinedTextField(
//                    query = searchQuery,
//                    onQueryChanged = onSearchQueryChanged
//                )
//            }
//        }
//    }
//}

@Preview
@Composable
fun PreviewTransactionScreen() {
    ReduceTheme {
        TransactionScreen()
    }
}
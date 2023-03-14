package com.ahr.reduce.presentation.screen.detail_address

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.navigation.Graph
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun DetailAddressScreen(
    navigator: Navigator,
    isRegisterFlow: Boolean,
    modifier: Modifier = Modifier,
    detailAddressViewModel: DetailAddressViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.detail_address,
                onNavigationClicked = navigator.navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->

        val onSaveClicked: () -> Unit = {
            if (!isRegisterFlow) {
                navigator.navigateUp()
            } else {
                navigator.navigateToMainGraph(Graph.Auth.route)
            }
        }


        DetailAddressContent(
            detailAddressViewModel = detailAddressViewModel,
            onSaveClicked = onSaveClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDetailAddressScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        DetailAddressScreen(
            isRegisterFlow = false,
            navigator = Navigator(navController)
        )
    }
}

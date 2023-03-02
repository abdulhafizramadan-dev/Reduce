package com.ahr.reduce.ui.screen.detail_address

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun DetailAddressScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(title = R.string.detail_address, onNavigationClicked = { })
        },
        modifier = modifier
    ) { paddingValues ->

        val location by remember { mutableStateOf("Suka Bakti, Kec. Curug, KabupatenTanggerang, Banten") }
        var completeAddress by remember { mutableStateOf("") }
        var subdistrict by remember { mutableStateOf("") }
        var noteToCourier by remember { mutableStateOf("") }

        DetailAddressContent(
            location = location,
            completeAddress = completeAddress,
            onCompleteAddressChanged = { completeAddress = it },
            subdistrict = subdistrict,
            onSubdistrictChanged = { subdistrict = it },
            noteToCourier = noteToCourier,
            onNoteToCourierChanged = { noteToCourier = it },
            onSaveClicked = {},
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewDetailAddressScreen() {
    ReduceTheme {
        DetailAddressScreen()
    }
}

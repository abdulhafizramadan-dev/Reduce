package com.ahr.reduce.ui.screen.detail_address

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun DetailAddressScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.detail_address,
                onNavigationClicked = {
                    navController.navigateUp()
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->

        var streetName by remember { mutableStateOf("Jl. Mawar Blok A12 No.99") }
        var ward by remember { mutableStateOf("Cinta") }
        var regency by remember { mutableStateOf("Curug") }
        var province by remember { mutableStateOf("Tangerang") }
        var subdistrict by remember { mutableStateOf("Jawa Barat") }
        var completeAddress by remember { mutableStateOf("Jl.Mawar Blok A12 No.99, Kel. Cinta, Kec. Curug, Kabupaten Tangerang, Jawa Barat 1189") }

        DetailAddressContent(
            streetName = streetName,
            onStreetNameChanged = { streetName = it },
            ward = ward,
            onWardChanged = { ward = it },
            subDistrict = subdistrict,
            onSubDistrictChanged = { subdistrict = it },
            regency = regency,
            onRegencyChanged = { regency = it },
            province = province,
            onProvinceChanged = { province = it },
            completeAddress = completeAddress,
            onCompleteAddressChanged = { completeAddress = it },
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
        DetailAddressScreen(
            navController = rememberNavController()
        )
    }
}

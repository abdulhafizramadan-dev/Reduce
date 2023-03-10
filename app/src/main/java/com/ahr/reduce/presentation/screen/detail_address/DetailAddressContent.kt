package com.ahr.reduce.presentation.screen.detail_address

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextFieldArea
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun DetailAddressContent(
    streetName: String,
    onStreetNameChanged: (String) -> Unit,
    ward: String,
    onWardChanged: (String) -> Unit,
    subDistrict: String,
    onSubDistrictChanged: (String) -> Unit,
    regency: String,
    onRegencyChanged: (String) -> Unit,
    province: String,
    onProvinceChanged: (String) -> Unit,
    completeAddress: String,
    onCompleteAddressChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.padding(top = 22.dp))

        ReduceOutlinedTextField(
            label = R.string.label_street_name,
            text = streetName,
            onTextChanged = onStreetNameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_ward,
            text = ward,
            onTextChanged = onWardChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_subdistrict,
            text = subDistrict,
            onTextChanged = onSubDistrictChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_regency,
            text = regency,
            onTextChanged = onRegencyChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_province,
            text = province,
            onTextChanged = onProvinceChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(42.dp))

        ReduceOutlinedTextFieldArea(
            label = R.string.label_complete_address,
            text = completeAddress,
            onTextChanged = onCompleteAddressChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        ReduceFilledButton(
            title = R.string.save,
            onButtonClicked = onSaveClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewDetailAddressContent() {
    ReduceTheme {
        DetailAddressContent(
            streetName = "Jl. Mawar Blok A12 No.99",
            onStreetNameChanged = {},
            ward = "Cinta",
            onWardChanged = {},
            subDistrict = "Curug",
            onSubDistrictChanged = {},
            regency = "Tangerang",
            onRegencyChanged = {},
            province = "Jawa Barat",
            onProvinceChanged = {},
            completeAddress = "Jl.Mawar Blok A12 No.99, Kel. Cinta, Kec. Curug, Kabupaten Tangerang, Jawa Barat 1189",
            onCompleteAddressChanged = {},
            onSaveClicked = {}
        )
    }
}
package com.ahr.reduce.ui.screen.detail_address

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.button.ReduceFilledButton
import com.ahr.reduce.ui.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.ui.component.textfield.ReduceOutlinedTextFieldArea
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun DetailAddressContent(
    location: String,
    completeAddress: String,
    onCompleteAddressChanged: (String) -> Unit,
    subdistrict: String,
    onSubdistrictChanged: (String) -> Unit,
    noteToCourier: String,
    onNoteToCourierChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        DetailUserLocation(
            location = location,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.padding(top = 26.dp))

        ReduceOutlinedTextFieldArea(
            label = R.string.label_complete_address,
            text = completeAddress,
            onTextChanged = onCompleteAddressChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_subdistrict,
            text = subdistrict,
            onTextChanged = onSubdistrictChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextFieldArea(
            label = R.string.label_note_to_courier,
            text = noteToCourier,
            onTextChanged = onNoteToCourierChanged,
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
                .padding(bottom = 61.dp)
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun DetailUserLocation(
    location: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f))
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(
                start = 20.dp,
                top = 18.dp
            )
        )
        Text(
            text = location,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
            modifier = Modifier.padding(
                start = 26.dp,
                top = 6.dp,
                end = 26.dp,
                bottom = 18.dp
            )
        )
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.25f))
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewDetailAddressContent() {
    ReduceTheme {
        DetailAddressContent(
            location = "Suka Bakti, Kec. Curug, KabupatenTanggerang, Banten",
            completeAddress = "",
            onCompleteAddressChanged = {},
            subdistrict = "",
            onSubdistrictChanged = {},
            noteToCourier = "",
            onNoteToCourierChanged = {},
            onSaveClicked = {}
        )
    }
}
package com.ahr.reduce.presentation.screen.detail_address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextFieldArea
import com.stevdzasan.messagebar.MessageBarState
import kotlinx.coroutines.delay

@Composable
fun DetailAddressContent(
    detailAddressViewModel: DetailAddressViewModel,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    messageBarState: MessageBarState,
) {

    val scrollState = rememberScrollState()

    val detailAddressForm by detailAddressViewModel.detailAddressForm.collectAsState()

    val isStreetNameNotValid = detailAddressViewModel.isStreetNameNotValid
    val isWardNotValid = detailAddressViewModel.isWardNotValid
    val isSubdistrictNotValid = detailAddressViewModel.isSubdistrictNotValid
    val isRegencyNotValid = detailAddressViewModel.isRegencyNotValid
    val isProvinceNotValid = detailAddressViewModel.isProvinceNotValid
    val isCompleteAddressNotValid = detailAddressViewModel.isCompleteAddressNotValid

    val allFormValid by detailAddressViewModel.allFormValid.collectAsState(initial = false)

    val saveButtonLoadingState = detailAddressViewModel.saveButtonLoadingState
    val saveUserState = detailAddressViewModel.saveDetailAddressState

    LaunchedEffect(key1 = saveUserState) {
        when (saveUserState) {
            is UiState.Idle,
            is UiState.Loading -> {}
            is UiState.Success -> {
                detailAddressViewModel.updateSaveButtonLoadingState(false)
                messageBarState.addSuccess("Sukses menyimpan detail alamat!")
                delay(3000L)
                onSaveClicked()
            }
            is UiState.Error -> {
                detailAddressViewModel.updateSaveButtonLoadingState(false)
                messageBarState.addError(saveUserState.exception as Exception)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.padding(top = 22.dp))

        ReduceOutlinedTextField(
            label = R.string.label_street_name,
            text = detailAddressForm.streetName,
            onTextChanged = detailAddressViewModel::updateStreetName,
            isError = isStreetNameNotValid,
            errorMessage = R.string.empty_street_name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_ward,
            text = detailAddressForm.ward,
            onTextChanged = detailAddressViewModel::updateWard,
            isError = isWardNotValid,
            errorMessage = R.string.empty_ward,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_subdistrict,
            text = detailAddressForm.subdistrict,
            onTextChanged = detailAddressViewModel::updateSubdistrict,
            isError = isSubdistrictNotValid,
            errorMessage = R.string.empty_subdistrict,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_regency,
            text = detailAddressForm.regency,
            onTextChanged = detailAddressViewModel::updateRegency,
            isError = isRegencyNotValid,
            errorMessage = R.string.empty_regency,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        ReduceOutlinedTextField(
            label = R.string.label_province,
            text = detailAddressForm.province,
            onTextChanged = detailAddressViewModel::updateProvince,
            isError = isProvinceNotValid,
            errorMessage = R.string.empty_province,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(42.dp))

        ReduceOutlinedTextFieldArea(
            label = R.string.label_complete_address,
            text = detailAddressForm.completeAddress,
            onTextChanged = detailAddressViewModel::updateCompleteAddress,
            isError = isCompleteAddressNotValid,
            errorMessage = R.string.empty_complete_address,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp)
                .padding(horizontal = 16.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.weight(1f))

        ReduceFilledButton(
            title = R.string.save,
            onButtonClicked = {
                detailAddressViewModel.updateSaveButtonLoadingState(true)
                detailAddressViewModel.saveDetailAddress()
            },
            enabled = allFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            loadingState = saveButtonLoadingState
        )
    }
}

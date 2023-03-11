package com.ahr.reduce.presentation.screen.profile_setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.dropdown.ReduceExposedDropDownMenuBox
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.util.FormAuthTextField
import com.ahr.reduce.util.Gender
import com.ahr.reduce.util.isEmailFormat

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingContent(
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    profileSettingViewModel: ProfileSettingViewModel
) {

    val scrollState = rememberScrollState()

    val profileSettingForm by profileSettingViewModel.profileSettingForm.collectAsState()

    val isFirstNameNotValid = profileSettingViewModel.isFirstNameNotValid
    val isLastNameNotValid = profileSettingViewModel.isLastNameNotValid
    val isEmailNotValid = profileSettingViewModel.isEmailNotValid
    val isTelephoneNotValid = profileSettingViewModel.isTelephoneNotValid
    val isBirthDateNotValid = profileSettingViewModel.isBirthDateNotValid

    val allFormValid by profileSettingViewModel.allFormValid.collectAsState(initial = false)

    val emailErrorMessage by remember(key1 = profileSettingForm.email) {
        derivedStateOf {
            if (profileSettingForm.email.isEmpty()) {
                R.string.empty_email
            } else {
                if (!profileSettingForm.email.isEmailFormat()) {
                    R.string.invalid_email
                } else {
                    R.string.empty_string
                }
            }
        }
    }

    val formProfiles = listOf(
        FormAuthTextField(
            label = R.string.label_first_name,
            text = profileSettingForm.firstName,
            onTextChanged = profileSettingViewModel::updateFirstName,
            isError = isFirstNameNotValid,
            errorMessage = R.string.empty_first_name
        ),
        FormAuthTextField(
            label = R.string.label_last_name,
            text = profileSettingForm.lastName,
            onTextChanged = profileSettingViewModel::updateLastName,
            isError = isLastNameNotValid,
            errorMessage = R.string.empty_last_name
        ),
        FormAuthTextField(
            label = R.string.label_email,
            text = profileSettingForm.email,
            onTextChanged = profileSettingViewModel::updateEmail,
            readOnly = true,
            isError = isEmailNotValid,
            errorMessage = emailErrorMessage,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        ),
        FormAuthTextField(
            label = R.string.label_telephone,
            text = profileSettingForm.telephone,
            onTextChanged = profileSettingViewModel::updateTelephone,
            isError = isTelephoneNotValid,
            errorMessage = R.string.empty_telephone,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        ),
        FormAuthTextField(
            label = R.string.label_birth_date,
            text = profileSettingForm.birthDate,
            onTextChanged = profileSettingViewModel::updateBirthDate,
            readOnly = true,
            isError = isBirthDateNotValid,
            errorMessage = R.string.empty_birth_date
        )
    )

    val genders = Gender.values().map { it.gender }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.padding(top = 22.dp))

        formProfiles.forEach { form ->
            ReduceOutlinedTextField(
                label = form.label,
                text = form.text,
                onTextChanged = form.onTextChanged,
                readOnly = form.readOnly,
                isError = form.isError,
                errorMessage = form.errorMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 13.dp)
            )
        }

        ReduceExposedDropDownMenuBox(
            label = R.string.label_gender,
            selectedOption = profileSettingForm.gender,
            onOptionSelected = profileSettingViewModel::updateGender,
            options = genders
        )

        Spacer(modifier = Modifier.weight(1f))

        ReduceFilledButton(
            title = R.string.save,
            onButtonClicked = onSaveClicked,
            enabled = allFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 28.dp),
        )
    }
}

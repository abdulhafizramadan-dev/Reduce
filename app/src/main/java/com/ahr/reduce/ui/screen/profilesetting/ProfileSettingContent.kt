package com.ahr.reduce.ui.screen.profilesetting

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.button.ReduceFilledButton
import com.ahr.reduce.ui.component.dropdown.ReduceExposedDropDownMenuBox
import com.ahr.reduce.ui.component.textfield.ReduceTextField
import com.ahr.reduce.ui.theme.ReduceTheme
import com.ahr.reduce.util.FormAuthTextField
import com.ahr.reduce.util.Gender

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingContent(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    telephone: String,
    onTelephoneChanged: (String) -> Unit,
    birthDate: String,
    onBirthDateChanged: (String) -> Unit,
    gender: String,
    onGenderChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    val formProfiles = listOf(
        FormAuthTextField(
            label = R.string.label_first_name,
            text = firstName,
            onTextChanged = onFirstNameChanged
        ),
        FormAuthTextField(
            label = R.string.label_last_name,
            text = lastName,
            onTextChanged = onLastNameChanged
        ),
        FormAuthTextField(
            label = R.string.label_email,
            text = email,
            onEmailChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        ),
        FormAuthTextField(
            label = R.string.label_telephone,
            text = telephone,
            onTextChanged = onTelephoneChanged,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        ),
        FormAuthTextField(
            label = R.string.label_birth_date,
            text = birthDate,
            onTextChanged = onBirthDateChanged,
            readOnly = true
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
            ReduceTextField(
                label = form.label,
                text = form.text,
                onTextChanged = form.onTextChanged,
                readOnly = form.readOnly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 13.dp)
            )
        }

        ReduceExposedDropDownMenuBox(
            label = R.string.label_gender,
            selectedOption = gender,
            onOptionSelected = onGenderChanged,
            options = genders
        )

        Spacer(modifier = Modifier.weight(1f))

        ReduceFilledButton(
            title = R.string.save,
            onButtonClicked = onSaveClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 61.dp)
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    ReduceTheme {
        ProfileSettingContent(
            firstName = "",
            onFirstNameChanged = {},
            lastName = "",
            onLastNameChanged = {},
            email = "",
            onEmailChanged = {},
            telephone = "",
            onTelephoneChanged = {},
            birthDate = "",
            onBirthDateChanged = {},
            gender = "",
            onGenderChanged = {},
            onSaveClicked = {})
    }
}
package com.ahr.reduce.presentation.screen.profile_setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.dropdown.ReduceExposedDropDownMenuBox
import com.ahr.reduce.presentation.component.textfield.ReduceDatePickerTextField
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.util.Gender
import com.ahr.reduce.util.isEmailFormat
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.stevdzasan.messagebar.MessageBarState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun ProfileSettingContent(
    onSaveClicked: () -> Unit,
    modifier: Modifier = Modifier,
    profileSettingViewModel: ProfileSettingViewModel,
    messageBarState: MessageBarState
) {
    val scrollState = rememberScrollState()

    val profileSettingForm by profileSettingViewModel.profileSettingForm.collectAsState()

    val isFirstNameNotValid = profileSettingViewModel.isFirstNameNotValid
    val isLastNameNotValid = profileSettingViewModel.isLastNameNotValid
    val isEmailNotValid = profileSettingViewModel.isEmailNotValid
    val isTelephoneNotValid = profileSettingViewModel.isTelephoneNotValid

    val allFormValid by profileSettingViewModel.allFormValid.collectAsState(initial = false)

    val saveButtonLoadingState = profileSettingViewModel.saveButtonLoadingState
    val saveUserState = profileSettingViewModel.saveUserProfileState

    LaunchedEffect(key1 = Unit) {
        when (saveUserState) {
            is UiState.Idle,
            is UiState.Loading -> {}
            is UiState.Success -> {
                profileSettingViewModel.updateSaveButtonLoadingState(false)
                onSaveClicked()
            }
            is UiState.Error -> {
                profileSettingViewModel.updateSaveButtonLoadingState(false)
                messageBarState.addError(saveUserState.exception as Exception)
            }
        }
    }

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

    val genders = Gender.values().map { it.gender }

    val selectedDate = remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    val sheetState = rememberSheetState()

    CalendarDialog(
        state = sheetState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Date(
            selectedDate = selectedDate.value
        ) { newDate ->
            selectedDate.value = newDate
        },
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {

        Spacer(modifier = Modifier.padding(top = 22.dp))

        ReduceOutlinedTextField(
            label = R.string.label_first_name,
            text = profileSettingForm.firstName,
            onTextChanged = profileSettingViewModel::updateFirstName,
            errorMessage = R.string.empty_first_name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            isError = isFirstNameNotValid
        )

        ReduceOutlinedTextField(
            label = R.string.label_last_name,
            text = profileSettingForm.lastName,
            onTextChanged = profileSettingViewModel::updateLastName,
            errorMessage = R.string.empty_last_name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            isError = isLastNameNotValid
        )

        ReduceOutlinedTextField(
            label = R.string.label_email,
            text = profileSettingForm.email,
            onTextChanged = profileSettingViewModel::updateEmail,
            readOnly = true,
            errorMessage = emailErrorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            isError = isEmailNotValid
        )

        ReduceOutlinedTextField(
            label = R.string.label_telephone,
            text = profileSettingForm.telephone,
            onTextChanged = profileSettingViewModel::updateTelephone,
            errorMessage = R.string.empty_telephone,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            isError = isTelephoneNotValid
        )

        ReduceDatePickerTextField(
            label = R.string.label_birth_date,
            text = selectedDate.value.toString(),
            errorMessage = R.string.empty_birth_date,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 13.dp),
            onTextFieldClicked = { sheetState.show() }
        )

        ReduceExposedDropDownMenuBox(
            label = R.string.label_gender,
            selectedOption = profileSettingForm.gender,
            onOptionSelected = profileSettingViewModel::updateGender,
            options = genders
        )

        Spacer(modifier = Modifier.weight(1f))

        ReduceFilledButton(
            title = R.string.save,
            onButtonClicked = {
                profileSettingViewModel.updateSaveButtonLoadingState(true)
                onSaveClicked()
            },
            enabled = allFormValid,
            loadingState = saveButtonLoadingState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 28.dp),
        )
    }
}

@Composable
fun ReduceOutlinedTextFieldDatePicker(

) {

}

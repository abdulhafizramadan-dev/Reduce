package com.ahr.reduce.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.button.ReduceFilledButton
import com.ahr.reduce.presentation.component.button.ReduceTextButton
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextFieldPassword
import com.ahr.reduce.presentation.component.text.AuthSubtitle
import com.ahr.reduce.presentation.component.text.AuthTitle
import com.ahr.reduce.ui.theme.Gray20
import com.ahr.reduce.util.isEmailFormat

@Composable
fun RegisterContent(
    registerViewModel: RegisterViewModel,
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val scrollState = rememberScrollState()

    val registerForm by registerViewModel.registerForm.collectAsState()

    val isFirstNameNotValid = registerViewModel.isFirstNameNotValid
    val isLastNameNotValid = registerViewModel.isLastNameNotValid
    val isEmailNotValid = registerViewModel.isEmailNotValid
    val isPasswordNotValid = registerViewModel.isPasswordNotValid
    val isConfirmPasswordNotValid = registerViewModel.isConfirmPasswordNotValid

    val signUpLoadingState = registerViewModel.signUpLoadingState

    val allFormValid by registerViewModel.allFormValid.collectAsState(initial = false)

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)
    ) {

        RegisterHeader(modifier = Modifier.fillMaxWidth())
        RegisterForm(
            firstName = registerForm.firstName,
            onFirstNameChanged = registerViewModel::updateFirstName,
            isFirstNameNotValid = isFirstNameNotValid,
            lastName = registerForm.lastName,
            onLastNameChanged = registerViewModel::updateLastName,
            isLastNameNotValid = isLastNameNotValid,
            email = registerForm.email,
            isEmailNotValid = isEmailNotValid,
            onEmailChanged = registerViewModel::updateEmail,
            password = registerForm.password,
            isPasswordNotValid = isPasswordNotValid,
            onPasswordChanged = registerViewModel::updatePassword,
            confirmPassword = registerForm.confirmPassword,
            onConfirmPasswordChanged = registerViewModel::updateConfirmPassword,
            isConfirmPasswordNotValid = isConfirmPasswordNotValid,
        )
        Spacer(modifier = Modifier.weight(1f))
        RegisterFooter(
            onRegisterClicked = onRegisterClicked,
            registerButtonEnabled = allFormValid,
            onLoginClicked = onLoginClicked,
            signUpLoadingState = signUpLoadingState
        )
    }
}

@Composable
fun RegisterHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTitle(
            text = R.string.register_title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp)
        )
        AuthSubtitle(
            text = R.string.register_subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_hero_register),
            contentDescription = null,
            modifier = Modifier
                .height(174.dp)
                .padding(top = 24.dp),

        )
    }
}

@Composable
fun RegisterForm(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isFirstNameNotValid: Boolean,
    isLastNameNotValid: Boolean,
    isEmailNotValid: Boolean,
    isPasswordNotValid: Boolean,
    isConfirmPasswordNotValid: Boolean
) {

    val emailErrorMessage by remember(key1 = email) {
        derivedStateOf {
            if (email.isEmpty()) {
                R.string.empty_email
            } else {
                if (!email.isEmailFormat()) {
                    R.string.invalid_email
                } else {
                    R.string.empty_string
                }
            }
        }
    }

    val passwordErrorMessage by remember(key1 = password) {
        derivedStateOf {
            if (password.isEmpty()) {
                R.string.empty_password
            } else {
                if (!password.isEmailFormat()) {
                    R.string.invalid_password
                } else {
                    R.string.empty_string
                }
            }
        }
    }
    
    Column(modifier = modifier) {
        ReduceOutlinedTextField(
            label = R.string.label_first_name,
            text = firstName,
            onTextChanged = onFirstNameChanged,
            isError = isFirstNameNotValid,
            errorMessage = R.string.empty_first_name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        ReduceOutlinedTextField(
            label = R.string.label_last_name,
            text = lastName,
            onTextChanged = onLastNameChanged,
            isError = isLastNameNotValid,
            errorMessage = R.string.empty_last_name,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        ReduceOutlinedTextField(
            label = R.string.label_email,
            text = email,
            onTextChanged = onEmailChanged,
            isError = isEmailNotValid,
            errorMessage = emailErrorMessage,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_password,
            text = password,
            onTextChanged = onPasswordChanged,
            isError = isPasswordNotValid,
            errorMessage = passwordErrorMessage,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_confirm_password,
            text = confirmPassword,
            onTextChanged = onConfirmPasswordChanged,
            isError = isConfirmPasswordNotValid,
            errorMessage = R.string.not_match_password,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@Composable
fun RegisterFooter(
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    modifier: Modifier = Modifier,
    registerButtonEnabled: Boolean,
    signUpLoadingState: Boolean
) {

    Column(modifier = modifier) {
        ReduceFilledButton(
            title = R.string.register,
            onButtonClicked = onRegisterClicked,
            enabled = registerButtonEnabled,
            loadingState = signUpLoadingState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.already_have_an_account),
                style = MaterialTheme.typography.bodySmall,
                color = Gray20
            )
            ReduceTextButton(
                title = R.string.login,
                onButtonClicked = onLoginClicked,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )
        }
    }
}

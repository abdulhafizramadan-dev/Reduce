package com.ahr.reduce.presentation.screen.login

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
import com.ahr.reduce.presentation.component.button.ReduceSignInWithGoogleButton
import com.ahr.reduce.presentation.component.button.ReduceTextButton
import com.ahr.reduce.presentation.component.text.AuthSubtitle
import com.ahr.reduce.presentation.component.text.AuthTitle
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.presentation.component.textfield.ReduceOutlinedTextFieldPassword
import com.ahr.reduce.ui.theme.Gray20
import com.ahr.reduce.util.isEmailFormat
import com.stevdzasan.onetap.OneTapSignInState

@Composable
fun LoginContent(
    loginViewModel: LoginViewModel,
    oneTapSignInState: OneTapSignInState,
    onForgotPassword: () -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    val loginForm by loginViewModel.loginForm.collectAsState()

    val isEmailNotValid = loginViewModel.isEmailNotValid
    val isPasswordNotValid = loginViewModel.isPasswordNotValid

    val allFormValid by loginViewModel.allFormValid.collectAsState(initial = false)

    val openSignInWithGoogleDialog = {
        oneTapSignInState.open()
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)
    ) {
        LoginHeader(modifier = Modifier.fillMaxWidth())
        LoginForm(
            email = loginForm.email,
            onEmailChanged = loginViewModel::updateEmail,
            password = loginForm.password,
            isEmailNotValid = isEmailNotValid,
            onPasswordChanged = loginViewModel::updatePassword,
            onForgotPassword = onForgotPassword,
            isPasswordNotValid = isPasswordNotValid
        )
        LoginFooter(
            onLoginClicked = onLoginClicked,
            onRegisterClicked = onRegisterClicked,
            isLoginButtonEnabled = allFormValid,
            onSignInWithGoogleClicked = openSignInWithGoogleDialog,
            isSignInWithGoogleEnabled = !oneTapSignInState.opened,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        AuthTitle(
            text = R.string.login_title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp)
        )
        AuthSubtitle(
            text = R.string.login_subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_hero_login),
            contentDescription = null,
            modifier = modifier
                .height(260.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LoginForm(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
    isEmailNotValid: Boolean,
    isPasswordNotValid: Boolean
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
            label = R.string.label_email,
            text = email,
            onTextChanged = onEmailChanged,
            isError = isEmailNotValid,
            errorMessage = emailErrorMessage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 62.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_password,
            text = password,
            onTextChanged = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            isError = isPasswordNotValid,
            errorMessage = passwordErrorMessage
        )

        ReduceTextButton(
            title = R.string.forgot_password,
            onButtonClicked = onForgotPassword,
            modifier = Modifier
                .padding(all = 4.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .align(Alignment.End)
        )
    }
}

@Composable
fun LoginFooter(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onSignInWithGoogleClicked: () -> Unit,
    isLoginButtonEnabled: Boolean,
    isSignInWithGoogleEnabled: Boolean,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {

        ReduceFilledButton(
            title = R.string.login,
            onButtonClicked = onLoginClicked,
            enabled = isLoginButtonEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.dont_have_account),
                style = MaterialTheme.typography.bodySmall,
                color = Gray20
            )
            ReduceTextButton(
                title = R.string.register,
                onButtonClicked = onRegisterClicked,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        ReduceSignInWithGoogleButton(
            onButtonClicked = onSignInWithGoogleClicked,
            enabled = isSignInWithGoogleEnabled
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}
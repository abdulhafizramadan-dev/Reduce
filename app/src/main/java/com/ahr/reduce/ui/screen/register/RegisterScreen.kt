package com.ahr.reduce.ui.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigateToProfileSettingsScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    RegisterContent(
        firstName = firstName,
        onFirstNameChanged = { firstName = it },
        lastName = lastName,
        onLastNameChanged = { lastName = it },
        email = email,
        onEmailChanged = { email = it },
        password = password,
        onPasswordChanged = { password = it },
        confirmPassword = confirmPassword,
        onConfirmPasswordChanged = { confirmPassword = it },
        onRegisterClicked = navigateToProfileSettingsScreen,
        onLoginClicked = navigateToLoginScreen,
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    ReduceTheme {
        RegisterScreen(
            navigateToProfileSettingsScreen = {},
            navigateToLoginScreen = {}
        )
    }
}
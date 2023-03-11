package com.ahr.reduce.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.AuthScreen.Login
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()

    val isEmailNotValid by loginViewModel.isEmailNotValid.collectAsState()
    val isPasswordNotValid by loginViewModel.isPasswordNotValid.collectAsState()

    val isLoginButtonEnabled by remember(key1 = isEmailNotValid, key2 = isPasswordNotValid) {
        derivedStateOf {
            email.isNotEmpty() && email.isEmailFormat()
                    &&
            password.isNotEmpty() && password.isPasswordFormat()
        }
    }

    LoginContent(
        email = email,
        onEmailChanged = loginViewModel::updateEmail,
        isEmailNotValid = isEmailNotValid,
        password = password,
        onPasswordChanged = loginViewModel::updatePassword,
        isPasswordNotValid = isPasswordNotValid,
        onForgotPassword = { },
        onLoginClicked = {
            navigator.navigateToMainGraph(Login.route)
        },
        onRegisterClicked = navigator.navigateToRegisterScreen,
        isLoginButtonEnabled = isLoginButtonEnabled,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            navigator = Navigator(navController)
        )
    }
}
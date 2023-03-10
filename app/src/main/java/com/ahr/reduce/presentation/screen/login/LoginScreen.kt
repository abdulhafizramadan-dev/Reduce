package com.ahr.reduce.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.AuthScreen.Login
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginContent(
        email = email,
        onEmailChanged = { email = it },
        password = password,
        onPasswordChanged = { password = it },
        onForgotPassword = { },
        onLoginClicked = {
            navigator.navigateToMainGraph(Login.route)
        },
        onRegisterClicked = navigator.navigateToRegisterScreen,
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
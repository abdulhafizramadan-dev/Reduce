package com.ahr.reduce.presentation.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.AuthScreen.Login
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    val oneTapSignInState = rememberOneTapSignInState()

    OneTapSignInWithGoogle(
        state = oneTapSignInState,
        clientId = "",
        onTokenIdReceived = {},
        onDialogDismissed = {}
    )

    LoginContent(
        loginViewModel = loginViewModel,
        oneTapSignInState = oneTapSignInState,
        onForgotPassword = {},
        onRegisterClicked = navigator.navigateToRegisterScreen,
        modifier = modifier,
        onLoginClicked = {
            navigator.navigateToMainGraph(Login.route)
        }
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
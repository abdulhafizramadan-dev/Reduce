package com.ahr.reduce.presentation.screen.login

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.AuthScreen.Login
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme
import com.ahr.reduce.util.AuthResultContract
import com.google.android.gms.common.api.ApiException
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val messageBarState = rememberMessageBarState()

    LaunchedEffect(key1 = loginUiState) {
        when (loginUiState) {
            is UiState.Idle,
            is UiState.Loading -> {}
            is UiState.Success -> {
                loginViewModel.updateSignInWithEmailAndPasswordLoadingState(false)
                loginViewModel.updateSignInWithGoogleLoadingState(false)
                navigator.navigateToMainGraph(Login.route)
            }
            is UiState.Error -> {
                loginViewModel.updateSignInWithEmailAndPasswordLoadingState(false)
                loginViewModel.updateSignInWithGoogleLoadingState(false)
                val message = (loginUiState as UiState.Error).exception.message
                messageBarState.addError(Exception(message))
            }
        }
    }

    val authResultLauncher = rememberLauncherForActivityResult(
        contract = AuthResultContract()
    ) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account != null) {
                loginViewModel.updateSignInWithGoogleLoadingState(true)
                val idToken = account.idToken.toString()
                loginViewModel.signInWithGoogle(idToken = idToken)
            } else {
                Log.d("TAG", "LoginScreen: token Empty")
            }
        } catch (e: ApiException) {
            Log.d("TAG", "LoginScreen: error=${e.message}")
        }
    }

    val onLoginClicked = {
        loginViewModel.updateSignInWithEmailAndPasswordLoadingState(true)
        loginViewModel.signInWithEmailAndPassword()
    }

    val onLoginWithGoogleClicked = {
        authResultLauncher.launch(Activity.RESULT_OK)
    }

    ContentWithMessageBar(
        messageBarState = messageBarState,
        visibilityDuration = 1000L,
        showToastOnCopy = false,
        errorMaxLines = 2,
    ) {
        LoginContent(
            loginViewModel = loginViewModel,
            onForgotPassword = {},
            onRegisterClicked = navigator.navigateToRegisterScreen,
            modifier = modifier,
            onLoginClicked = onLoginClicked,
            onSignInWithGoogleClicked = onLoginWithGoogleClicked
        )
    }

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
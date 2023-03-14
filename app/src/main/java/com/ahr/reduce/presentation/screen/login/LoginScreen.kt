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

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    val loginUiState by loginViewModel.loginUiState.collectAsState()

    LaunchedEffect(key1 = loginUiState) {
        when (loginUiState) {
            is UiState.Idle -> {}
            is UiState.Loading -> {
                loginViewModel.updateSignInWithGoogleLoadingState(true)
            }
            is UiState.Success -> {
                loginViewModel.updateSignInWithGoogleLoadingState(false)
                Log.d("TAG", "LoginScreen: Success")
                navigator.navigateToMainGraph(Login.route)
            }
            is UiState.Error -> {
                Log.d("TAG", "LoginScreen: Error = ${(loginUiState as UiState.Error).exception.message}")
            }
        }
    }

    val authResultLauncher = rememberLauncherForActivityResult(
        contract = AuthResultContract()
    ) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account != null) {
                val tokenId = account.idToken.toString()
                loginViewModel.signInWithGoogle(tokenId = tokenId)
            } else {
                val tokenId = account?.idToken.toString()
                Log.d("TAG", "LoginScreen: token Empty = $tokenId")
            }
        } catch (e: ApiException) {
            Log.d("TAG", "LoginScreen: error=${e.message}")
        }
    }

    val onLoginClicked = {}

    val onSignInWithGoogleClicked = {
        authResultLauncher.launch(Activity.RESULT_OK)
    }

    LoginContent(
        loginViewModel = loginViewModel,
        onForgotPassword = {},
        onRegisterClicked = navigator.navigateToRegisterScreen,
        modifier = modifier,
        onLoginClicked = onLoginClicked,
        onSignInWithGoogleClicked = onSignInWithGoogleClicked
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
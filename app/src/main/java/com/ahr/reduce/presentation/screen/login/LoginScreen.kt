package com.ahr.reduce.presentation.screen.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
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

    val signInRequestCode = 1

    val authResultLauncher = rememberLauncherForActivityResult(
        contract = AuthResultContract()
    ) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
//                text = "Google sign in failed"
            } else {
                Log.d("TAG", "LoginScreen: token=${account.idToken}")
//                coroutineScope.launch {
//                    authViewModel.signIn(
//                        email = account.email,
//                        displayName = account.displayName,
//                    )
//                }
            }
        } catch (e: ApiException) {
//            text = "Google sign in failed"
            Log.d("TAG", "LoginScreen: error=${e.message}")
        } finally {
            loginViewModel.updateSignInWithGoogleLoadingState(false)
        }
    }

    val onLoginClicked = {
        navigator.navigateToMainGraph(Login.route)
    }

    val onSignInWithGoogleClicked = {
        loginViewModel.updateSignInWithGoogleLoadingState(true)
//        oneTapSignInState.open()
        authResultLauncher.launch(signInRequestCode)
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
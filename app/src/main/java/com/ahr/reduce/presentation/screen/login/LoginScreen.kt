package com.ahr.reduce.presentation.screen.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.domain.data.SignInWithGoogleResponse
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.AuthScreen.Login
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigator: Navigator,
) {

    val loginUiState by loginViewModel.loginUiState.collectAsState()
    val googleSignInClient = loginViewModel.googleSignInClient
    val messageBarState = rememberMessageBarState()

    LaunchedEffect(key1 = loginUiState) {
        when (loginUiState) {
            is UiState.Idle,
            is UiState.Loading -> {}
            is UiState.Success -> {
                loginViewModel.updateSignInWithEmailAndPasswordLoadingState(false)
                loginViewModel.updateSignInWithGoogleLoadingState(false)
                if ((loginUiState as UiState.Success<SignInWithGoogleResponse>).data.isNewUser){
                    navigator.navigateToProfileSettingsRegisterFlow()
                } else {
                    navigator.navigateToMainGraph(Login.route)
                }
            }
            is UiState.Error -> {
                loginViewModel.updateSignInWithEmailAndPasswordLoadingState(false)
                loginViewModel.updateSignInWithGoogleLoadingState(false)
                val message = (loginUiState as UiState.Error).exception.message
                messageBarState.addError(Exception(message))
            }
        }
    }

    val signInWithGoogleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val accountResult = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(accountResult.idToken, null)
                loginViewModel.signInWithGoogle(authCredential = credentials)
            } catch (exception: ApiException) {
                messageBarState.addError(exception)
            }
        } else {
            loginViewModel.updateSignInWithGoogleLoadingState(false)
        }
    }

    val onLoginClicked = {
        loginViewModel.updateSignInWithEmailAndPasswordLoadingState(true)
        loginViewModel.signInWithEmailAndPassword()
    }

    val onLoginWithGoogleClicked = {
        loginViewModel.updateSignInWithGoogleLoadingState(true)
        signInWithGoogleLauncher.launch(googleSignInClient.signInIntent)
    }

    ContentWithMessageBar(
        messageBarState = messageBarState,
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
package com.ahr.reduce.presentation.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigator: Navigator
) {

    val registerUiState by registerViewModel.registerUiState.collectAsState()
    val messageBarState = rememberMessageBarState()
    val context = LocalContext.current

    LaunchedEffect(key1 = registerUiState) {
        when (registerUiState) {
            is UiState.Idle,
            is UiState.Loading -> {}
            is UiState.Success -> {
                registerViewModel.updateSignUpLoadingState(false)
                messageBarState.addSuccess(context.getString(R.string.message_success_register_user))
            }
            is UiState.Error -> {
                registerViewModel.updateSignUpLoadingState(false)
                val message = (registerUiState as UiState.Error).exception.message
                messageBarState.addError(Exception(message))
            }
        }
    }

    ContentWithMessageBar(
        messageBarState = messageBarState,
        showToastOnCopy = false,
        errorMaxLines = 2,
    ) {
        RegisterContent(
            registerViewModel = registerViewModel,
            onLoginClicked = navigator.navigateUp,
            modifier = modifier,
            onRegisterClicked = {
                registerViewModel.updateSignUpLoadingState(true)
                registerViewModel.signUpUser()
            }
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        RegisterScreen(
            navigator = Navigator(navController)
        )
    }
}
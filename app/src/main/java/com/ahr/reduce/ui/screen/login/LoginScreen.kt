package com.ahr.reduce.ui.screen.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LoginContent(
        email = email,
        onEmailChanged = { email = it },
        password = password,
        onPasswordChanged = { password = it },
        onLoginClicked = { },
        onRegisterClicked = { },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    ReduceTheme {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
}
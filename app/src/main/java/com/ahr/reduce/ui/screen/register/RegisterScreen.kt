package com.ahr.reduce.ui.screen.register

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.AuthScreen.*
import com.ahr.reduce.navigation.Graph.*
import com.ahr.reduce.navigation.IndependentScreen.*
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navigator: Navigator
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
        onRegisterClicked = {
            navigator.navigateToProfileSettingsRegisterFlow()
        },
        onLoginClicked = navigator.navigateUp,
        modifier = modifier
    )

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
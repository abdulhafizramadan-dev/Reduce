package com.ahr.reduce.ui.screen.register

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.AuthScreen
import com.ahr.reduce.navigation.IndependentScreen
import com.ahr.reduce.navigation.IndependentScreen.ProfileSettings
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val registerClicked: () -> Unit = {
        navController.popBackStack()
        val profileSettingsRoute = ProfileSettings.getRoute(
            prevScreen = AuthScreen.Register.route,
            nextScreen = IndependentScreen.DetailAddress.route
        )
        navController.navigate(profileSettingsRoute)
    }

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
        onRegisterClicked = registerClicked,
        onLoginClicked = { navController.navigateUp() },
        modifier = modifier
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    ReduceTheme {
        RegisterScreen(
            navController = rememberNavController()
        )
    }
}
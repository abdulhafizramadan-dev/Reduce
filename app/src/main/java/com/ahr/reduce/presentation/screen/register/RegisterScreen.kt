package com.ahr.reduce.presentation.screen.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    navigator: Navigator
) {

    RegisterContent(
        registerViewModel = registerViewModel,
        onLoginClicked = navigator.navigateUp,
        modifier = modifier,
        onRegisterClicked = {
            navigator.navigateToProfileSettingsRegisterFlow()
        }
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
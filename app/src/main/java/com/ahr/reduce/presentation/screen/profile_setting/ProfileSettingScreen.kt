package com.ahr.reduce.presentation.screen.profile_setting

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme
import com.ahr.reduce.util.Gender

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingScreen(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    isRegisterFlow: Boolean = false,
) {
    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = R.string.profile_settings,
                onNavigationClicked = navigator.navigateUp
            )
        },
        modifier = modifier
    ) { paddingValues ->

        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var telephone by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf(Gender.MAN.gender) }

        val onSaveClicked: () -> Unit = if (!isRegisterFlow) {
            navigator.navigateUp
        } else {
            navigator.navigateToDetailAddressRegisterFlow
        }

        ProfileSettingContent(
            firstName = firstName,
            onFirstNameChanged = { firstName = it },
            lastName = lastName,
            onLastNameChanged = { lastName = it },
            email = email,
            onEmailChanged = { email = it },
            telephone = telephone,
            onTelephoneChanged = { telephone = it },
            birthDate = birthDate,
            onBirthDateChanged = { birthDate = it },
            gender = gender,
            onGenderChanged = { gender = it },
            onSaveClicked = onSaveClicked,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewProfileScreen() {
    ReduceTheme {
        val navController = rememberNavController()
        ProfileSettingScreen(
            navigator = Navigator(navController)
        )
    }
}
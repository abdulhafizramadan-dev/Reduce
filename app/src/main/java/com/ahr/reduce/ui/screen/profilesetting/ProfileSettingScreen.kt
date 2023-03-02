package com.ahr.reduce.ui.screen.profilesetting

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.ui.theme.ReduceTheme
import com.ahr.reduce.util.Gender

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ProfileSettingTopAppBar(onNavigationClicked = { })
        },
        modifier = modifier
    ) { paddingValues ->

        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var telephone by remember { mutableStateOf("") }
        var birthDate by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf(Gender.MAN.gender) }

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
            onSaveClicked = {},
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewProfileScreen() {
    ReduceTheme {
        ProfileSettingScreen()
    }
}

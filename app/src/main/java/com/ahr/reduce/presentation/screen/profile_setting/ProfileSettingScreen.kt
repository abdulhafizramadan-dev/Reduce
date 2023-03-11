package com.ahr.reduce.presentation.screen.profile_setting

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ahr.reduce.R
import com.ahr.reduce.navigation.Navigator
import com.ahr.reduce.presentation.component.topappbar.DetailTopAppBar
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileSettingScreen(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    profileSettingViewModel: ProfileSettingViewModel = hiltViewModel(),
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

        val onSaveClicked: () -> Unit = if (!isRegisterFlow) {
            navigator.navigateUp
        } else {
            navigator.navigateToDetailAddressRegisterFlow
        }

        ProfileSettingContent(
            profileSettingViewModel = profileSettingViewModel,
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

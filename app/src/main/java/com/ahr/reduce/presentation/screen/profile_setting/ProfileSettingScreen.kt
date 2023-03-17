package com.ahr.reduce.presentation.screen.profile_setting

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@RequiresApi(Build.VERSION_CODES.O)
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
            .statusBarsPadding()
            .navigationBarsPadding()
    ) { paddingValues ->

        val onSaveClicked: () -> Unit = if (!isRegisterFlow) {
            navigator.navigateUp
        } else {
            navigator.navigateToDetailAddressRegisterFlow
        }

        val messageBarState = rememberMessageBarState()

        ContentWithMessageBar(messageBarState = messageBarState) {
            ProfileSettingContent(
                profileSettingViewModel = profileSettingViewModel,
                messageBarState = messageBarState,
                onSaveClicked = onSaveClicked,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
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

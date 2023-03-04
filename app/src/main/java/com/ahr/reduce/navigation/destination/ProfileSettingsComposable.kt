package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.profile_setting.ProfileSettingScreen

@ExperimentalMaterial3Api
fun NavGraphBuilder.profileSettingsComposable(
    onNavigateUpClicked: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    composable(route = Screen.PROFILE_SETTINGS.route) {
        ProfileSettingScreen(
            onNavigateUpClicked = onNavigateUpClicked,
            onSaveClicked = onSaveClicked,
            modifier = Modifier.fillMaxSize()
        )
    }
}
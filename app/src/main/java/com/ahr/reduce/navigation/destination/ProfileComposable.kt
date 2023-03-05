package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileComposable(
    onLogoutClicked: () -> Unit,
    modifier: Modifier
) {
    composable(route = Screen.PROFILE.route) {
        ProfileScreen(
            modifier = modifier.fillMaxSize(),
            onLogoutClickedClicked = onLogoutClicked
        )
    }
}
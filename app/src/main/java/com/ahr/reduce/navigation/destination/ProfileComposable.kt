package com.ahr.reduce.navigation.destination

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen

fun NavGraphBuilder.profileComposable(
    onLogoutClicked: () -> Unit,
    modifier: Modifier
) {
    composable(route = Screen.PROFILE.route) {
//        (
//            modifier = Modifier.fillMaxSize()
//        )
    }
}
package com.ahr.reduce.navigation.destination

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ahr.reduce.navigation.Screen
import com.ahr.reduce.ui.screen.detail_address.DetailAddressScreen

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.addressDetailComposable(
    onNavigateUpClicked: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    composable(route = Screen.DETAIL_ADDRESS.route) {
        DetailAddressScreen(
            onNavigateUpClicked = onNavigateUpClicked,
            onSaveClicked = onSaveClicked,
            modifier = Modifier.fillMaxSize()
        )
    }
}
package com.ahr.reduce.ui.screen.profile

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onLogoutClickedClicked: () -> Unit
) {
    ProfileContent(
        modifier = modifier,
        onLogoutClickedClicked = onLogoutClickedClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewProfileScreen() {
    ReduceTheme {
        ProfileScreen(
            onLogoutClickedClicked = {}
        )
    }
}

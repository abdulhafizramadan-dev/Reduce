package com.ahr.reduce.ui.screen.profilesetting

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ahr.reduce.R

@Composable
fun ProfileSettingTopAppBar(
    onNavigationClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    SmallTopAppBar(
        navigationIcon = {
            ProfileScreenNavigationIcon(
                onNavigationClicked = onNavigationClicked
            )
        },
        title = {
            Text(text = stringResource(R.string.profile_settings))
        },
        modifier = modifier
    )
}

@Composable
fun ProfileScreenNavigationIcon(
    onNavigationClicked: () -> Unit
) {
    IconButton(onClick = onNavigationClicked) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back)
        )
    }
}
package com.ahr.reduce.ui.component.topappbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun DetailTopAppBar(
    @StringRes title: Int,
    onNavigationClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            DetailNavigationIcon(
                onNavigationClicked = onNavigationClicked
            )
        },
        title = {
            Text(text = stringResource(title))
        },
        modifier = modifier
    )
}

@Composable
fun DetailNavigationIcon(
    onNavigationClicked: () -> Unit
) {
    IconButton(onClick = onNavigationClicked) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailTopAppBar() {
    ReduceTheme {
        DetailTopAppBar(
            title = R.string.profile_settings,
            onNavigationClicked = { }
        )
    }
}
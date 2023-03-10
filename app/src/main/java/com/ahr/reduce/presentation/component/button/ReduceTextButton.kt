package com.ahr.reduce.presentation.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceTextButton(
    @StringRes title: Int,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.bodySmall,
        color = color,
        modifier = modifier.clickable { onButtonClicked() }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewReduceTextButton() {
    ReduceTheme {
        ReduceTextButton(
            title = R.string.forgot_password,
            onButtonClicked = { },
        )
    }
}
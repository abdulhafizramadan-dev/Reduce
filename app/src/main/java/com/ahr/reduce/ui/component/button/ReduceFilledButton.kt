package com.ahr.reduce.ui.component.button

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceFilledButton(
    @StringRes title: Int,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    FilledTonalButton(
        onClick = onButtonClicked,
        enabled = enabled,
        modifier = modifier
            .height(50.dp),
    ) {
        Text(text = stringResource(id = title))
    }
}

@Preview
@Composable
fun PreviewReduceFilledButton() {
    ReduceTheme {
        ReduceFilledButton(
            title = R.string.register,
            onButtonClicked = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

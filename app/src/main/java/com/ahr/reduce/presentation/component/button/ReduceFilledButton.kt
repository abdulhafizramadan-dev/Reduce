package com.ahr.reduce.presentation.component.button

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    loadingState: Boolean = false,
) {
    val buttonTitle = if (loadingState) {
        R.string.loading
    } else {
        title
    }
    val buttonBackground = if (loadingState) {
        MaterialTheme.colorScheme.primary.copy(alpha = .5f)
    } else {
        MaterialTheme.colorScheme.primary
    }
    Button(
        onClick = onButtonClicked,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonBackground,
        ),
        modifier = modifier
            .height(50.dp)
            .animateContentSize(),
    ) {
        Text(text = stringResource(id = buttonTitle))
        if (loadingState) {
            Spacer(modifier = Modifier.width(16.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp,
                color = Color.White
            )
        }
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

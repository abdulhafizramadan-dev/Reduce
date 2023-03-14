package com.ahr.reduce.presentation.component.button

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceSignInWithGoogleButton(
    modifier: Modifier = Modifier,
    @StringRes title: Int = R.string.signin_with_google,
    onButtonClicked: () -> Unit,
    isLoading: Boolean,
    enabled: Boolean = true,
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = .8f)),
        modifier = modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .clickable(enabled = enabled) {
                onButtonClicked()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = 24.dp,
                    vertical = 10.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .width(width = 19.dp)
                    .height(height = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(R.string.google_logo),
                    modifier = Modifier
                        .size(size = 25.dp)
                )
            }
            Spacer(modifier = Modifier.width(width = 8.dp))
            Text(
                text = stringResource(title),
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                style = MaterialTheme.typography.labelLarge
            )
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewReduceSignInWithGoogleButton() {
    ReduceTheme {
        ReduceSignInWithGoogleButton(
            onButtonClicked = {},
            modifier = Modifier.padding(all = 16.dp),
            enabled = false,
            isLoading = true
        )
    }
}
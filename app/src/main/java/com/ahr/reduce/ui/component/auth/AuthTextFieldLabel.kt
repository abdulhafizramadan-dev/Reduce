package com.ahr.reduce.ui.component.auth

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun AuthTextFieldLabel(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.titleSmall.copy(
            lineHeight = 21.sp,
            ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthTextFieldLabel() {
    ReduceTheme {
        AuthTextFieldLabel(
            text = R.string.label_first_name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

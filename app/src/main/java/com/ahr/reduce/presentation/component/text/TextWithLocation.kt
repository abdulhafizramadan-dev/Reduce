package com.ahr.reduce.presentation.component.text

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun TextWithLocation(
    location: String,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.sent_to))
        append(" ")
        withStyle(style = SpanStyle(fontWeight = FontWeight.Medium)) {
            append(location)
        }
    }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = annotatedString,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextWithLocation() {
    ReduceTheme {
        TextWithLocation(
            location = "Curug, Kabupaten Tanggerang",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
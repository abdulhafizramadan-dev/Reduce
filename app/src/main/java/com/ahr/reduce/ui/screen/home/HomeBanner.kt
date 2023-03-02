package com.ahr.reduce.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun HomeBanner(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(bottom = 8.dp)
            .padding(horizontal = 36.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_beranda),
            contentDescription = null,
            modifier = Modifier
                .size(143.dp)
                .align(Alignment.BottomEnd),
        )
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(top = 30.dp),
        ) {
            Text(
                text = stringResource(R.string.welcome),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 28.sp,
                    color = Color.White
                )
            )
            val description = buildAnnotatedString {
                append("Waktunya untuk menyelamatkan\n")
                append("bumi, dimulai dengan\n")
                append("mengurangi sampah !!\n")
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(
                    lineHeight = 17.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                ),
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeBanner() {
    ReduceTheme {
        HomeBanner()
    }
}

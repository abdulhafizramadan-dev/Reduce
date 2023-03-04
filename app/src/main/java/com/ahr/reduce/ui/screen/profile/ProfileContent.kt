package com.ahr.reduce.ui.screen.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onLogoutClickedClicked: () -> Unit
) {

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        ProfileHeader(
            photo = R.drawable.avatar,
            name = "Anatasya Sabrina",
            telephone = "+628129034567",
            email = "anatasyasabrina123@gmail.com",
            onEditClicked = {},
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(
                    vertical = 16.dp,
                    horizontal = 16.dp
                )
        )
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        ProfileContentActivity(
            title = R.string.profile_settings,
            activities = listOf(
                "Alamat",
                "Voucher",
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        ProfileContentActivity(
            title = R.string.about_reduce,
            activities = listOf(
                "Syarat & Ketentuan",
                "Kebijakan Pribadi",
                "Pusat Bantuan",
                "Hubungi Kami",
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
        ProfileContentActivity(
            title = R.string.option,
            activities = listOf(
                "Keluar",
                "Tema",
                "Pengaturan",
                "Pusat Bantuan",
                "Bahasa",
            ),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ProfileContentActivity(
    @StringRes title: Int,
    activities: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                lineHeight = 16.sp
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        activities.forEach { activity ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = activity,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 16.sp
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .clickable { }
                        .padding(vertical = 8.dp)
                )
                Icon(imageVector = Icons.Filled.NavigateNext, contentDescription = null)
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    ReduceTheme {
        ProfileContent(onLogoutClickedClicked = {})
    }
}

@Preview
@Composable
fun PreviewProfileContentActivity() {
    ReduceTheme {
        ProfileContentActivity(
            title = R.string.profile_settings,
            activities = listOf(
                "Alamat",
                "Voucher",
            )
        )
    }
}
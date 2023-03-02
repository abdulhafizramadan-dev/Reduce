package com.ahr.reduce.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.button.ReduceFilledButton
import com.ahr.reduce.ui.component.button.ReduceTextButton
import com.ahr.reduce.ui.component.textfield.AuthSubtitle
import com.ahr.reduce.ui.component.textfield.ReduceTextField
import com.ahr.reduce.ui.component.textfield.ReduceTextFieldPassword
import com.ahr.reduce.ui.component.textfield.AuthTitle
import com.ahr.reduce.ui.theme.Gray20
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)
    ) {

        AuthTitle(
            text = R.string.login_title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp)
        )
        AuthSubtitle(
            text = R.string.login_subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        )
        ReduceTextField(
            label = R.string.label_email,
            text = email,
            onTextChanged = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 62.dp)
        )
        ReduceTextFieldPassword(
            label = R.string.label_password,
            text = password,
            onTextChanged = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        ReduceTextButton(
            title = R.string.forgot_password,
            onButtonClicked = { },
            modifier = Modifier
                .padding(all = 4.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .align(Alignment.End)
        )

        ReduceFilledButton(
            title = R.string.login,
            onButtonClicked = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.dont_have_account),
                style = MaterialTheme.typography.bodySmall,
                color = Gray20
            )
            ReduceTextButton(
                title = R.string.register,
                onButtonClicked = { },
                modifier = Modifier
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    ReduceTheme {
        LoginScreen()
    }
}
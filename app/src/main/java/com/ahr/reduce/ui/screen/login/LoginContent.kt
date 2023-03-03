package com.ahr.reduce.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.button.ReduceFilledButton
import com.ahr.reduce.ui.component.button.ReduceTextButton
import com.ahr.reduce.ui.component.textfield.AuthSubtitle
import com.ahr.reduce.ui.component.textfield.AuthTitle
import com.ahr.reduce.ui.component.textfield.ReduceOutlinedTextField
import com.ahr.reduce.ui.component.textfield.ReduceOutlinedTextFieldPassword
import com.ahr.reduce.ui.theme.Gray20

@Composable
fun LoginContent(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onForgotPassword: () -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)
    ) {
        LoginHeader(modifier = Modifier.fillMaxWidth())
        LoginForm(
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            onForgotPassword = onForgotPassword
        )
        LoginFooter(
            onLoginClicked = onLoginClicked,
            onRegisterClicked = onRegisterClicked
        )
    }
}

@Composable
fun LoginHeader(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
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

        Image(
            painter = painterResource(id = R.drawable.ic_hero_login),
            contentDescription = null,
            modifier = modifier
                .height(260.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun LoginForm(
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    onForgotPassword: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ReduceOutlinedTextField(
            label = R.string.label_email,
            text = email,
            onTextChanged = onEmailChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 62.dp)
        )
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_password,
            text = password,
            onTextChanged = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        ReduceTextButton(
            title = R.string.forgot_password,
            onButtonClicked = onForgotPassword,
            modifier = Modifier
                .padding(all = 4.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .align(Alignment.End)
        )
    }
}

@Composable
fun LoginFooter(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        ReduceFilledButton(
            title = R.string.login,
            onButtonClicked = onLoginClicked,
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
                onButtonClicked = onRegisterClicked,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )
        }
    }
}
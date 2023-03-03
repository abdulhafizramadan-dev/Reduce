package com.ahr.reduce.ui.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun RegisterContent(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .verticalScroll(scrollState)
    ) {

        RegisterHeader(modifier = Modifier.fillMaxWidth())
        RegisterForm(
            firstName = firstName,
            onFirstNameChanged = onFirstNameChanged,
            lastName = lastName,
            onLastNameChanged = onLastNameChanged,
            email = email,
            onEmailChanged = onEmailChanged,
            password = password,
            onPasswordChanged = onPasswordChanged,
            confirmPassword = confirmPassword,
            onConfirmPasswordChanged = onConfirmPasswordChanged
        )
        Spacer(modifier = Modifier.weight(1f))
        RegisterFooter(
            onRegisterClicked = onRegisterClicked,
            onLoginClicked = onLoginClicked
        )
    }
}

@Composable
fun RegisterHeader(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthTitle(
            text = R.string.register_title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 42.dp)
        )
        AuthSubtitle(
            text = R.string.register_subtitle,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_hero_register),
            contentDescription = null,
            modifier = Modifier
                .height(174.dp)
                .padding(top = 24.dp),

        )
    }
}

@Composable
fun RegisterForm(
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ReduceOutlinedTextField(
            label = R.string.label_first_name,
            text = firstName,
            onTextChanged = onFirstNameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        ReduceOutlinedTextField(
            label = R.string.label_last_name,
            text = lastName,
            onTextChanged = onLastNameChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
        )
        ReduceOutlinedTextField(
            label = R.string.label_email,
            text = email,
            onTextChanged = onEmailChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
        )
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_password,
            text = password,
            onTextChanged = onPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp)
        )
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_confirm_password,
            text = confirmPassword,
            onTextChanged = onConfirmPasswordChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@Composable
fun RegisterFooter(
    onRegisterClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        ReduceFilledButton(
            title = R.string.register,
            onButtonClicked = onRegisterClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.already_have_an_account),
                style = MaterialTheme.typography.bodySmall,
                color = Gray20
            )
            ReduceTextButton(
                title = R.string.login,
                onButtonClicked = onLoginClicked,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
            )
        }
    }
}

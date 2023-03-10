package com.ahr.reduce.presentation.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.text.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.Gray90
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceOutlinedTextFieldPassword(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
) {

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    Column {
        AuthTextFieldLabel(text = label)
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = modifier,
            shape = MaterialTheme.shapes.extraLarge,
            trailingIcon = {
                val trailingIcon = if (isShowPassword) {
                    Icons.Filled.VisibilityOff
                } else {
                    Icons.Filled.Visibility
                }
                IconButton(onClick = { isShowPassword = !isShowPassword }) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = stringResource(id = R.string.clear_text_field),
                        tint = Gray90
                    )
                }
            },
            visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = keyboardOptions,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReduceOutlinedTextFieldPassword() {
    ReduceTheme {
        ReduceOutlinedTextFieldPassword(
            label = R.string.label_password,
            text = "Password",
            onTextChanged = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

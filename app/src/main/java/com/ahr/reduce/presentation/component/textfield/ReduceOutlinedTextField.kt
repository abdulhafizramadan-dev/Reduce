package com.ahr.reduce.presentation.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.text.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.Gray90
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceOutlinedTextField(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    @StringRes errorMessage: Int = R.string.empty_field,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    largeGapLabel: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    ) {
    Column(modifier = modifier) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(if (largeGapLabel) 13.dp else 3.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (text.isNotEmpty() && trailingIcon == null) {
                    IconButton(onClick = { onTextChanged("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = stringResource(id = R.string.clear_text_field),
                            tint = Gray90
                        )
                    }
                }
                trailingIcon?.invoke()
            },
            isError = isError,
            singleLine = singleLine,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
        )
        if (isError) {
            Text(
                text = stringResource(errorMessage),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReduceOutlinedTextField() {
    ReduceTheme {
        ReduceOutlinedTextField(
            label = R.string.label_first_name,
            text = "Abdul",
            onTextChanged = {},
            modifier = Modifier.fillMaxWidth(),
            isError = false,
        )
    }
}

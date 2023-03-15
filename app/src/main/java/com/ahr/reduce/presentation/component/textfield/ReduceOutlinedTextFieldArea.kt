package com.ahr.reduce.presentation.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.text.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceOutlinedTextFieldArea(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    isError: Boolean = false,
    errorMessage: Int = R.string.empty_field,
    ) {
    val editTextHeight = ((MaterialTheme.typography.bodyLarge.lineHeight * 5).value - 2).dp
    Column(modifier = modifier) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(13.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .height(editTextHeight)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            isError = isError,
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
fun PreviewReduceOutlinedTextFieldArea() {
    ReduceTheme {
        var name by remember { mutableStateOf("") }
        ReduceOutlinedTextFieldArea(
            label = R.string.label_first_name,
            text = name,
            isError = true,
            onTextChanged = { name = it },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

package com.ahr.reduce.presentation.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.text.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceOutlinedTextFieldArea(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    heightIn: Dp = ((MaterialTheme.typography.bodyLarge.lineHeight * 6).value - 2).dp,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    ) {
    Column(modifier = modifier.height(heightIn)) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(13.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = leadingIcon,
            singleLine = singleLine,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions
        )
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
            onTextChanged = { name = it },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

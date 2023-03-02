package com.ahr.reduce.ui.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.Gray90
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceOutlinedTextField(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),

) {
    Column(modifier = modifier) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = leadingIcon,
            trailingIcon = {
                if (text.isNotEmpty() && singleLine) {
                    IconButton(onClick = { onTextChanged("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = stringResource(id = R.string.clear_text_field),
                            tint = Gray90
                        )
                    }
                }
            },
            singleLine = singleLine,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions
        )
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
            modifier = Modifier.fillMaxWidth()
        )
    }
}

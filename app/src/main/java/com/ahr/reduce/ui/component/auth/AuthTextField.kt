package com.ahr.reduce.ui.component.auth

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.Gray90
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun AuthTextField(
    @StringRes label: Int,
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        AuthTextFieldLabel(text = label)
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = modifier,
            shape = MaterialTheme.shapes.extraLarge,
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { onTextChanged("") }) {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = stringResource(id = R.string.clear_text_field),
                            tint = Gray90
                        )
                    }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuthTextField() {
    ReduceTheme {
        AuthTextField(
            label = R.string.label_first_name,
            text = "Abdul",
            onTextChanged = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

package com.ahr.reduce.ui.component.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@Composable
fun ReduceSearchOutlinedTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        leadingIcon = { Icon(imageVector = Icons.Outlined.Menu, contentDescription = null) },
        trailingIcon = {
           Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        },
        placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewReduceSearchOutlinedTextField() {
    ReduceTheme {
        var query by remember { mutableStateOf("") }
        ReduceSearchOutlinedTextField(query = query, onQueryChanged = { query = it })
    }
}
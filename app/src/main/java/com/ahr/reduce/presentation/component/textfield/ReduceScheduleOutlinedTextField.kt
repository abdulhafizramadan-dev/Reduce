package com.ahr.reduce.presentation.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ahr.reduce.R
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceScheduleOutlinedTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onScheduleClicked: () ->  Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onScheduleClicked() },
        shape = MaterialTheme.shapes.extraLarge,
        leadingIcon = { Icon(
            imageVector = Icons.Outlined.Today,
            contentDescription = null
        ) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.NavigateNext,
                contentDescription = null
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.schedule_picker),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        enabled = false,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledBorderColor = Color(0xFF49454F).copy(alpha = 0.12f),
            disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
            disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            containerColor = Color(0xFF49454F).copy(alpha = 0.12f)
        ),
    )

}

@Preview(showBackground = true)
@Composable
fun PreviewReduceScheduleOutlinedTextField() {
    ReduceTheme {
        var query by remember { mutableStateOf("") }
        ReduceScheduleOutlinedTextField(
            query = query,
            onQueryChanged = { query = it },
            onScheduleClicked = {}
        )
    }
}
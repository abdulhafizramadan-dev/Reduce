package com.ahr.reduce.presentation.component.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.presentation.component.text.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.ReduceTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReduceDatePickerTextField(
    @StringRes label: Int,
    text: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    @StringRes errorMessage: Int = R.string.empty_field,
    largeGapLabel: Boolean = false,
    onTextFieldClicked: () -> Unit,
    trailingIcon: ImageVector? = null,
    angle: Float = 0.0f
) {
    Column(modifier = modifier) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(if (largeGapLabel) 13.dp else 3.dp))
        Row(
            modifier = Modifier
                .heightIn(TextFieldDefaults.MinHeight)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.extraLarge)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    MaterialTheme.shapes.extraLarge
                )
                .clickable { onTextFieldClicked() }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = text)
            if (trailingIcon != null) {
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.rotate(angle)
                )
            }
        }
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
fun PreviewReduceDatePickerTextField() {
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

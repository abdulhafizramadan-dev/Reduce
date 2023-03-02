package com.ahr.reduce.ui.component.dropdown

import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahr.reduce.R
import com.ahr.reduce.ui.component.textfield.AuthTextFieldLabel
import com.ahr.reduce.ui.theme.Gray90
import com.ahr.reduce.ui.theme.ReduceTheme

@ExperimentalMaterial3Api
@Composable
fun ReduceExposedDropDownMenuBox(
    @StringRes label: Int,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier
) {

    var isExposed by remember { mutableStateOf(false) }
    val animatedAngle by animateFloatAsState(targetValue = if (isExposed) 180f else 0f)

    ExposedDropdownMenuBox(
        expanded = isExposed,
        onExpandedChange = { isExposed = !isExposed },
        modifier = modifier
    ) {
        ReduceDropDownTextField(
            label = label,
            text = selectedOption,
            angle = animatedAngle,
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = isExposed,
            onDismissRequest = { isExposed = !isExposed },
            modifier = Modifier.fillMaxWidth(0.92f)
        ) {
            options.forEach { option ->
                ReduceExposedDropDownMenuItem(
                    text = option,
                    onItemSelected = { gender ->
                        onOptionSelected(gender)
                        isExposed = !isExposed
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ReduceDropDownTextField(
    @StringRes label: Int,
    text: String,
    angle: Float,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        AuthTextFieldLabel(text = label, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(3.dp))
        OutlinedTextField(
            value = text,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.clear_text_field),
                    tint = Gray90,
                    modifier = Modifier.rotate(angle)
                )
            },
            singleLine = true,
            readOnly = true,
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun ReduceExposedDropDownMenuItem(
    text: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable { onItemSelected(text) }
            .padding(horizontal = 12.dp)
            .height(48.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Text(text = text, style = MaterialTheme.typography.labelLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewReduceExposedDropDownMenuBox() {
    ReduceTheme {
        ReduceExposedDropDownMenuBox(
            label = R.string.label_gender,
            selectedOption = "Laki-Laki",
            options = listOf("Laki-Laki", "Perempuan"),
            onOptionSelected = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

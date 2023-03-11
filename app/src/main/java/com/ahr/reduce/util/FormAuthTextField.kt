package com.ahr.reduce.util

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import com.ahr.reduce.R

data class FormAuthTextField(
    @StringRes val label: Int,
    val text: String,
    val onTextChanged: (String) -> Unit,
    val readOnly: Boolean = false,
    val singleLine: Boolean = true,
    val maxLines: Int = 1,
    val isError: Boolean = false,
    @StringRes val errorMessage: Int = R.string.empty_field,
    val leadingIcon: @Composable (() -> Unit)? = null,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
)

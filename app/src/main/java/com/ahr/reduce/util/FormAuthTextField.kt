package com.ahr.reduce.util

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

data class FormAuthTextField(
    @StringRes val label: Int,
    val text: String,
    val onTextChanged: (String) -> Unit,
    val readOnly: Boolean = false,
    val keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
)

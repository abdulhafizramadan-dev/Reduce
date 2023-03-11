package com.ahr.reduce.util

import android.util.Patterns

fun String.isEmailFormat(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isPasswordFormat(): Boolean {
    return length >= 8
}
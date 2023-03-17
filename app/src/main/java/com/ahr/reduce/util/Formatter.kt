package com.ahr.reduce.util

import java.text.NumberFormat
import java.util.*

fun Long.toRupiah(): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    formatter.maximumFractionDigits = 0
    return formatter.format(this)
}
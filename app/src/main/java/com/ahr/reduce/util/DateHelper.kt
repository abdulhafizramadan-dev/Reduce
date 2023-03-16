package com.ahr.reduce.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDate(): LocalDate? {
    return if (isNotEmpty()) {
        LocalDate.parse(this)
    } else {
        null
    }
}
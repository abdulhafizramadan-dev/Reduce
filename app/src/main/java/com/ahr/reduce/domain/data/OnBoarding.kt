package com.ahr.reduce.domain.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnBoarding(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)

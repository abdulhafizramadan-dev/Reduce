package com.ahr.reduce.domain.data

import com.ahr.reduce.util.Gender

data class ProfileSettingForm(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val telephone: String = "",
    val birthDate: String = "",
    val gender: String = Gender.MAN.gender,
)

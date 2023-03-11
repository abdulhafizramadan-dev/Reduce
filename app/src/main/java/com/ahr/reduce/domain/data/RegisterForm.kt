package com.ahr.reduce.domain.data

data class RegisterForm(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)

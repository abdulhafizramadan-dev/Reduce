package com.ahr.reduce.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ahr.reduce.domain.data.LoginForm
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginForm = MutableStateFlow(LoginForm())
    val loginForm get() = _loginForm.asStateFlow()

    var isEmailNotValid by mutableStateOf(false)
        private set
    var isPasswordNotValid by mutableStateOf(false)
        private set

    val allFormValid get() = _loginForm.map {  loginForm ->
        loginForm.email.isNotEmpty() &&
        loginForm.email.isEmailFormat() &&
        loginForm.password.isPasswordFormat()
    }

    fun updateEmail(email: String) {
        _loginForm.update { it.copy(email = email) }
        isEmailNotValid = email.isEmpty() || !email.isEmailFormat()
    }

    fun updatePassword(password: String) {
        _loginForm.update { it.copy(password = password) }
        isPasswordNotValid = password.isEmpty() || !password.isPasswordFormat()
    }

}
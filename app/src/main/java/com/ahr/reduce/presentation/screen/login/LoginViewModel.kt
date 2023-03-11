package com.ahr.reduce.presentation.screen.login

import androidx.lifecycle.ViewModel
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    val email get() = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password get() = _password.asStateFlow()

    private val _isEmailNotValid = MutableStateFlow(false)
    val isEmailNotValid get() = _isEmailNotValid.asStateFlow()

    private val _isPasswordNotValid = MutableStateFlow(false)
    val isPasswordNotValid get() = _isPasswordNotValid.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
        _isEmailNotValid.value = email.isEmpty() || !email.isEmailFormat()
    }

    fun updatePassword(password: String) {
        _password.value = password
        _isPasswordNotValid.value = password.isEmpty() || !password.isPasswordFormat()
    }

}
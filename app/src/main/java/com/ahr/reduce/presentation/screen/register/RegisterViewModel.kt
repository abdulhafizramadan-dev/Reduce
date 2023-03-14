package com.ahr.reduce.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.RegisterForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.RealmRepository
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val realmRepository: RealmRepository
) : ViewModel() {

    private val _registerForm = MutableStateFlow(RegisterForm())
    val registerForm get() = _registerForm.asStateFlow()

    private val _registerUiState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val registerUiState get() = _registerUiState.asStateFlow()

    var isFirstNameNotValid by mutableStateOf(false)
        private set
    var isLastNameNotValid by mutableStateOf(false)
        private set
    var isEmailNotValid by mutableStateOf(false)
        private set
    var isPasswordNotValid by mutableStateOf(false)
        private set
    var isConfirmPasswordNotValid by mutableStateOf(false)
        private set

    val allFormValid get() = _registerForm.map {  registerForm ->
        registerForm.firstName.isNotEmpty() &&
        registerForm.lastName.isNotEmpty() &&
        registerForm.email.isNotEmpty() &&
        registerForm.email.isEmailFormat() &&
        registerForm.password.isPasswordFormat() &&
        registerForm.password == registerForm.confirmPassword
    }

    fun updateFirstName(firstName: String) {
        _registerForm.update { it.copy(firstName = firstName) }
        isFirstNameNotValid = firstName.isEmpty()
    }

    fun updateLastName(lastName: String) {
        _registerForm.update { it.copy(lastName = lastName) }
        isLastNameNotValid = lastName.isEmpty()
    }

    fun updateEmail(email: String) {
        _registerForm.update { it.copy(email = email) }
        isEmailNotValid = email.isEmpty() || !email.isEmailFormat()
    }

    fun updatePassword(password: String) {
        _registerForm.update { it.copy(password = password) }
        isPasswordNotValid = !password.isPasswordFormat()
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _registerForm.update { it.copy(confirmPassword = confirmPassword) }
        isConfirmPasswordNotValid = confirmPassword != _registerForm.value.password
    }

}
package com.ahr.reduce.presentation.screen.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.RegisterForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _registerForm = MutableStateFlow(RegisterForm())
    val registerForm get() = _registerForm.asStateFlow()

    private val _registerUiState = MutableStateFlow<UiState<Boolean>>(UiState.Idle)
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

    var signUpLoadingState by mutableStateOf(false)
        private set

    val allFormValid get() = _registerForm.map {  registerForm ->
        registerForm.firstName.isNotEmpty() &&
        registerForm.lastName.isNotEmpty() &&
        registerForm.email.isNotEmpty() &&
        registerForm.email.isEmailFormat() &&
        registerForm.password.isPasswordFormat() &&
        registerForm.password == registerForm.confirmPassword
    }

    fun signUpUser() {
        viewModelScope.launch {
            firebaseRepository.signUpWithEmailAndPassword(registerForm = registerForm.value)
                .collect { apiState ->
                    when (apiState) {
                        is ApiState.Loading -> _registerUiState.value = UiState.Loading
                        is ApiState.Success -> _registerUiState.value = UiState.Success(apiState.data)
                        is ApiState.Error -> _registerUiState.value = UiState.Error(apiState.exception)
                    }
                }
        }
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

    fun updateSignUpLoadingState(state: Boolean) {
        signUpLoadingState = state
    }

}
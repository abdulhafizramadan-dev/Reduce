package com.ahr.reduce.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.LoginForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.RealmRepository
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val realmRepository: RealmRepository
) : ViewModel() {

    private val _loginForm = MutableStateFlow(LoginForm())
    val loginForm get() = _loginForm.asStateFlow()

    private val _loginUiState = MutableStateFlow<UiState<Boolean>>(UiState.Loading)
    val loginUiState get() = _loginUiState.asStateFlow()

    var isEmailNotValid by mutableStateOf(false)
        private set
    var isPasswordNotValid by mutableStateOf(false)
        private set

    var signInWithGoogleLoadingState by mutableStateOf(false)
        private set

    val allFormValid get() = _loginForm.map {  loginForm ->
        loginForm.email.isNotEmpty() &&
        loginForm.email.isEmailFormat() &&
        loginForm.password.isPasswordFormat()
    }

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
            realmRepository.signInWithGoogle(idToken).collectLatest { state ->
                _loginUiState.value = state
            }
        }
    }

    fun updateEmail(email: String) {
        _loginForm.update { it.copy(email = email) }
        isEmailNotValid = email.isEmpty() || !email.isEmailFormat()
    }

    fun updatePassword(password: String) {
        _loginForm.update { it.copy(password = password) }
        isPasswordNotValid = password.isEmpty() || !password.isPasswordFormat()
    }

    fun updateSignInWithGoogleLoadingState(state: Boolean) {
        signInWithGoogleLoadingState = state
    }

}
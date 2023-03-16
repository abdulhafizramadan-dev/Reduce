package com.ahr.reduce.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.LoginForm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.ahr.reduce.util.isEmailFormat
import com.ahr.reduce.util.isPasswordFormat
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val mGoogleSignInClient: GoogleSignInClient
) : ViewModel() {

    private val _loginForm = MutableStateFlow(LoginForm())
    val loginForm get() = _loginForm.asStateFlow()

    private val _loginUiState = MutableStateFlow<UiState<Boolean>>(UiState.Idle)
    val loginUiState get() = _loginUiState.asStateFlow()

    val googleSignInClient: GoogleSignInClient get() = mGoogleSignInClient

    var isEmailNotValid by mutableStateOf(false)
        private set
    var isPasswordNotValid by mutableStateOf(false)
        private set

    var signInWithEmailAndPasswordLoadingState by mutableStateOf(false)
        private set

    var signInWithGoogleLoadingState by mutableStateOf(false)
        private set

    val allFormValid get() = _loginForm.map {  loginForm ->
        loginForm.email.isNotEmpty() &&
        loginForm.email.isEmailFormat() &&
        loginForm.password.isPasswordFormat()
    }

    fun signInWithEmailAndPassword() {
        viewModelScope.launch {
            firebaseRepository
                .signInWithEmailAndPassword(loginForm.value)
                .collect { apiState ->
                    when (apiState) {
                        is ApiState.Loading -> _loginUiState.value = UiState.Loading
                        is ApiState.Success -> _loginUiState.value = UiState.Success(apiState.data)
                        is ApiState.Error -> _loginUiState.value = UiState.Error(apiState.exception)
                    }
                }
        }
    }

    fun signInWithGoogle(authCredential: AuthCredential) {
        viewModelScope.launch {
            firebaseRepository
                .signInWithGoogle(authCredential)
                .collect { apiState ->
                    when (apiState) {
                        is ApiState.Loading -> _loginUiState.value = UiState.Loading
                        is ApiState.Success -> _loginUiState.value = UiState.Success(apiState.data)
                        is ApiState.Error -> _loginUiState.value = UiState.Error(apiState.exception)
                    }
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

    fun updateSignInWithEmailAndPasswordLoadingState(state: Boolean) {
        signInWithEmailAndPasswordLoadingState = state
    }

    fun updateSignInWithGoogleLoadingState(state: Boolean) {
        signInWithGoogleLoadingState = state
    }

}
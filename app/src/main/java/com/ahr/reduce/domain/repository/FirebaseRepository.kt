package com.ahr.reduce.domain.repository

import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.LoginForm
import com.ahr.reduce.domain.data.RegisterForm
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun signUpWithEmailAndPassword(registerForm: RegisterForm): Flow<ApiState<Boolean>>
    fun signIpWithEmailAndPassword(loginForm: LoginForm): Flow<ApiState<Boolean>>
    fun saveUser(registerForm: RegisterForm): Boolean
}
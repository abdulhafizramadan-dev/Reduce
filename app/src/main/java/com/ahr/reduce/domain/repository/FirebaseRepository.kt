package com.ahr.reduce.domain.repository

import com.ahr.reduce.domain.data.*
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun signUpWithEmailAndPassword(registerForm: RegisterForm): Flow<ApiState<Boolean>>
    fun signInWithEmailAndPassword(loginForm: LoginForm): Flow<ApiState<Boolean>>
    fun signInWithGoogle(googleCredential: AuthCredential): Flow<ApiState<SignInWithGoogleResponse>>
    suspend fun saveUser(profileSettingForm: ProfileSettingForm): Boolean
    fun getUser(): Flow<ApiState<ProfileSettingForm>>
}
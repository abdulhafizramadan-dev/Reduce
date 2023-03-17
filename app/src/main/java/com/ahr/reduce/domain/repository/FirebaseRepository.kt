package com.ahr.reduce.domain.repository

import com.ahr.reduce.domain.data.*
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {
    fun signUpWithEmailAndPassword(registerForm: RegisterForm): Flow<ApiState<Boolean>>
    fun signInWithEmailAndPassword(loginForm: LoginForm): Flow<ApiState<Boolean>>
    fun signInWithGoogle(googleCredential: AuthCredential): Flow<ApiState<SignInWithGoogleResponse>>
    suspend fun saveUser(profileSettingForm: ProfileSettingForm): Boolean
    suspend fun saveUserAddress(detailAddressForm: DetailAddressForm): Boolean
    fun getUser(): Flow<ApiState<ProfileSettingForm>>
    fun getUserAddress(): Flow<ApiState<DetailAddressForm>>
    fun getHomeProduct(): Flow<ApiState<List<Product>>>
    fun getMarketProduct(): Flow<ApiState<List<Product>>>
    fun getProductDetail(documentId: String): Flow<ApiState<Product>>
}
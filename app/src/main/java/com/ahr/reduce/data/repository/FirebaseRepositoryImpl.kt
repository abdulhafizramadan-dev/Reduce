package com.ahr.reduce.data.repository

import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.ApiState.Error
import com.ahr.reduce.domain.data.LoginForm
import com.ahr.reduce.domain.data.RegisterForm
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : FirebaseRepository {
    override fun signUpWithEmailAndPassword(
        registerForm: RegisterForm
    ): Flow<ApiState<Boolean>> = flow {
        emit(ApiState.Loading)
        val signUpResult = firebaseAuth.createUserWithEmailAndPassword(
            registerForm.email,
            registerForm.password
        ).await()
        if (signUpResult.user != null) {
            saveUser(registerForm)
            emit(ApiState.Success(true))
        } else {
            emit(Error(UnknownError("Register failed!")))
        }
    }.catch { exception ->
        emit(Error(exception))
    }

    override fun signInWithEmailAndPassword(
        loginForm: LoginForm
    ): Flow<ApiState<Boolean>> = flow {
        emit(ApiState.Loading)
        val signUpResult = firebaseAuth.signInWithEmailAndPassword(
            loginForm.email,
            loginForm.password
        ).await()
        if (signUpResult.user != null) {
            emit(ApiState.Success(true))
        } else {
            emit(Error(UnknownError("Login failed!")))
        }
    }.catch { exception ->
        emit(Error(exception))
    }
    override fun signInWithGoogle(googleCredential: AuthCredential): Flow<ApiState<Boolean>> = flow {
        emit(ApiState.Loading)
        val authResult = firebaseAuth.signInWithCredential(googleCredential).await()
        val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
        if (isNewUser) {
            // TODO: Save user to collection
        }
        emit(ApiState.Success(true))
    }.catch  { exception ->
        emit(Error(exception))
    }

    override fun saveUser(registerForm: RegisterForm): Boolean {
        return true
    }
}
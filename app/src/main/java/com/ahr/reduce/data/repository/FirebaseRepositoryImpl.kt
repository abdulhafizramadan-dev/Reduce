package com.ahr.reduce.data.repository

import com.ahr.reduce.domain.data.ApiState
import com.ahr.reduce.domain.data.RegisterForm
import com.ahr.reduce.domain.repository.FirebaseRepository
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
            emit(ApiState.Error(UnknownError("Register failed!")))
        }
    }.catch { exception ->
        emit(ApiState.Error(exception))
    }

    override fun saveUser(registerForm: RegisterForm): Boolean {
        return true
    }
}
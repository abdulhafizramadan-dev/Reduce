package com.ahr.reduce.data.repository

import android.util.Log
import com.ahr.reduce.domain.data.*
import com.ahr.reduce.domain.data.ApiState.Error
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
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
    override fun signInWithGoogle(googleCredential: AuthCredential): Flow<ApiState<SignInWithGoogleResponse>> = flow {
        emit(ApiState.Loading)
        val authResult = firebaseAuth.signInWithCredential(googleCredential).await()
        val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false

        val signInWithGoogleResponse = SignInWithGoogleResponse(isSuccess = true)

        if (isNewUser) {
            Log.d("TAG", "signInWithGoogle: New User")
            signInWithGoogleResponse.isNewUser = true
            emit(ApiState.Success(signInWithGoogleResponse))
        } else {
            Log.d("TAG", "signInWithGoogle: Old User")
            emit(ApiState.Success(signInWithGoogleResponse))
        }
    }.catch  { exception ->
        Log.d("TAG", "signInWithGoogle: Error = ${exception.message}")
        emit(Error(exception))
    }

    override suspend fun saveUser(profileSettingForm: ProfileSettingForm): Boolean {
        return try {
            val user = mapOf(
                UserField.FIRST_NAME.field to profileSettingForm.firstName,
                UserField.LAST_NAME.field to profileSettingForm.lastName,
                UserField.EMAIL.field to profileSettingForm.email,
                UserField.TELEPHONE.field to profileSettingForm.telephone,
                UserField.BIRTH_DATE.field to profileSettingForm.birthDate,
                UserField.GENDER.field to profileSettingForm.gender,
            )
            firebaseFirestore.collection(FirebaseFirestoreConstant.userCollection)
                .add(user)
                .await()
            true
        } catch (exception: Exception) {
            false
        }
    }
}
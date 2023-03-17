package com.ahr.reduce.data.repository

import com.ahr.reduce.domain.data.*
import com.ahr.reduce.domain.data.ApiState.Error
import com.ahr.reduce.domain.repository.FirebaseRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
) : FirebaseRepository {

    private val userCollection get() = firebaseFirestore.collection(FirebaseFirestoreConstant.userCollection)
    private val detailAddressCollection get() = firebaseFirestore.collection(FirebaseFirestoreConstant.detailAddressCollection)
    private val productCollection get() = firebaseFirestore.collection(FirebaseFirestoreConstant.productCollection)

    private val storageRef get() = firebaseStorage.reference

    private val userUid get() = firebaseAuth.currentUser?.uid

    override fun signUpWithEmailAndPassword(
        registerForm: RegisterForm
    ): Flow<ApiState<Boolean>> = flow {
        emit(ApiState.Loading)
        val signUpResult = firebaseAuth.createUserWithEmailAndPassword(
            registerForm.email,
            registerForm.password
        ).await()
        if (signUpResult.user != null) {
            val profileSettingForm = ProfileSettingForm(
                firstName = registerForm.firstName,
                lastName = registerForm.lastName,
                email = registerForm.email
            )
            saveUser(profileSettingForm)
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
            signInWithGoogleResponse.isNewUser = true
            val email = firebaseAuth.currentUser?.email
            val baseProfileUser = ProfileSettingForm(
                email = email.toString()
            )
            saveUser(baseProfileUser)
            emit(ApiState.Success(signInWithGoogleResponse))
        } else {
            emit(ApiState.Success(signInWithGoogleResponse))
        }
    }.catch  { exception ->
        exception.printStackTrace()
        emit(Error(exception))
    }

    override suspend fun saveUser(profileSettingForm: ProfileSettingForm): Boolean {
        return try {
            userCollection.document(userUid.toString())
                .set(profileSettingForm)
                .await()
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun getUser(): Flow<ApiState<ProfileSettingForm>> = flow {
        emit(ApiState.Loading)
        val userProfile = userCollection.document(userUid.toString()).get()
            .await()
            .toObject(ProfileSettingForm::class.java)
        emit(ApiState.Success(userProfile ?: ProfileSettingForm()))
    }.catch { exception ->
        emit(Error(exception = exception))
    }

    override suspend fun saveUserAddress(detailAddressForm: DetailAddressForm): Boolean {
        return try {
            detailAddressCollection.document(userUid.toString())
                .set(detailAddressForm)
                .await()
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun getUserAddress(): Flow<ApiState<DetailAddressForm>> = flow {
        emit(ApiState.Loading)
        val detailAddress = detailAddressCollection.document(userUid.toString()).get()
            .await()
            .toObject(DetailAddressForm::class.java)
        emit(ApiState.Success(detailAddress ?: DetailAddressForm()))
    }.catch { exception ->
        emit(Error(exception = exception))
    }

    override fun getHomeProduct(): Flow<ApiState<List<Product>>> {
        TODO("Not yet implemented")
    }

    override fun getMarketProduct(): Flow<ApiState<List<Product>>> {
        TODO("Not yet implemented")
    }
}
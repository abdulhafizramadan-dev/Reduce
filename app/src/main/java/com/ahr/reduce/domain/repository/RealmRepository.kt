package com.ahr.reduce.domain.repository

import com.ahr.reduce.data.data.ProductRealm
import com.ahr.reduce.data.data.UserRealm
import com.ahr.reduce.domain.data.UiState
import io.realm.kotlin.mongodb.User
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface RealmRepository {
    fun configureRealm(user: User)
    fun signInWithGoogle(tokenId: String): Flow<UiState<Boolean>>
    fun signUpWithEmailAndPassword(userRealm: UserRealm): Flow<UiState<Boolean>>
    suspend fun saveUser(userRealm: UserRealm)
    fun getHomeProduct(): Flow<List<ProductRealm>>
    fun getAllProduct(): Flow<List<ProductRealm>>
    fun searchProduct(): Flow<List<ProductRealm>>
    fun getCartProduct(userId: ObjectId): Flow<List<ProductRealm>>
}
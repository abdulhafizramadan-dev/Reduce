package com.ahr.reduce.domain.repository

import com.ahr.reduce.data.data.Product
import com.ahr.reduce.data.data.User
import com.ahr.reduce.domain.data.UiState
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface RealmRepository {
    fun configureRealm()
    fun signInWithGoogle(tokenId: String): Flow<UiState<Boolean>>
    fun signUpWithEmailAndPassword(user: User): Flow<UiState<Boolean>>
    fun getHomeProduct(): Flow<List<Product>>
    fun getAllProduct(): Flow<List<Product>>
    fun searchProduct(): Flow<List<Product>>
    fun getCartProduct(userId: ObjectId): Flow<List<Product>>
}
package com.ahr.reduce.data.repository

import com.ahr.reduce.data.data.Product
import com.ahr.reduce.data.data.User
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.RealmRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

class RealmRepositoryImpl(private val realmApp: App) : RealmRepository {

    private val user get() = realmApp.currentUser
    private lateinit var realm: Realm

    override fun configureRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user = user!!,
                schema = setOf(Product::class)
            )
            .log(LogLevel.ALL)
            .build()
            realm = Realm.open(config)
        }
    }

    override fun signInWithGoogle(tokenId: String): Flow<UiState<Boolean>> = flow {
        emit(UiState.Loading)
        try {
            val result = withContext(Dispatchers.IO) {
                realmApp.login(
                    Credentials.google(token = tokenId, type = GoogleAuthType.ID_TOKEN)
                ).loggedIn
            }
            if (result) {
                emit(UiState.Success(true))
            } else {
                emit(UiState.Error(UnknownError("User is not logged in.")))
            }
        } catch (e: Exception) {
            emit(UiState.Error(exception = e))
        }
    }

    override fun signUpWithEmailAndPassword(user: User): Flow<UiState<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getHomeProduct(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getAllProduct(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun searchProduct(): Flow<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getCartProduct(userId: ObjectId): Flow<List<Product>> {
        TODO("Not yet implemented")
    }
}
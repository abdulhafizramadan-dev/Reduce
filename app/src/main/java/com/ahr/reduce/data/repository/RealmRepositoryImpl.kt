package com.ahr.reduce.data.repository

import com.ahr.reduce.data.data.ProductRealm
import com.ahr.reduce.data.data.UserRealm
import com.ahr.reduce.domain.data.UiState
import com.ahr.reduce.domain.repository.RealmRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.mongodb.kbson.ObjectId

class RealmRepositoryImpl(private val realmApp: App) : RealmRepository {

    private val user get() = realmApp.currentUser
    private lateinit var realm: Realm

    override fun configureRealm(user: User) {
        val config = SyncConfiguration.Builder(
            user = user,
            schema = setOf(UserRealm::class, ProductRealm::class)
        )
            .initialSubscriptions { realm ->
                add(query = realm.query<UserRealm>(query = "token_id == $0", user.id))
            }
            .log(LogLevel.ALL)
            .build()
        realm = Realm.open(config)
    }

    override fun signInWithGoogle(tokenId: String): Flow<UiState<Boolean>> = flow {
        emit(UiState.Loading)
        try {
            val result = withContext(Dispatchers.IO) {
                realmApp.login(
                    Credentials.google(token = tokenId, type = GoogleAuthType.ID_TOKEN)
                )
            }
            if (result.loggedIn) {
                configureRealm(result)
                val defaultUserRealm = UserRealm()
                defaultUserRealm.token_id = result.id
                saveUser(defaultUserRealm)
                emit(UiState.Success(true))
            } else {
                emit(UiState.Error(UnknownError("User is not logged in.")))
            }
        } catch (e: Exception) {
            emit(UiState.Error(exception = e))
        }
    }

    override fun signUpWithEmailAndPassword(userRealm: UserRealm): Flow<UiState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(userRealm: UserRealm) {
        realm.writeBlocking {
            copyToRealm(userRealm)
        }
    }

    override fun getHomeProduct(): Flow<List<ProductRealm>> {
        TODO("Not yet implemented")
    }

    override fun getAllProduct(): Flow<List<ProductRealm>> {
        TODO("Not yet implemented")
    }

    override fun searchProduct(): Flow<List<ProductRealm>> {
        TODO("Not yet implemented")
    }

    override fun getCartProduct(userId: ObjectId): Flow<List<ProductRealm>> {
        TODO("Not yet implemented")
    }
}
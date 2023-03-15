package com.ahr.reduce.data.repository

import android.util.Log
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class RealmRepositoryImpl(private val realmApp: App) : RealmRepository {

    override val user: User? get() = realmApp.currentUser
    private lateinit var realm: Realm

    override fun configureRealm(user: User) {
        val config = SyncConfiguration.Builder(
            user = user,
            schema = setOf(UserRealm::class, ProductRealm::class)
        )
            .initialSubscriptions { realm ->
                add(query = realm.query<UserRealm>(query = "owner_id == $0", user.id))
            }
            .log(LogLevel.ALL)
            .build()
        realm = Realm.open(config)
    }

    override fun signInWithGoogle(idToken: String): Flow<UiState<Boolean>> = flow {
        emit(UiState.Loading)
        try {
            val result = realmApp.login(
                Credentials.google(token = idToken, type = GoogleAuthType.ID_TOKEN)
            )
            if (result.loggedIn) {
                configureRealm(result)
//                val products = realm.query<ProductRealm>().find()
//                Log.d("TAG", "HomeScreen: homeProducts = $products")
                emit(UiState.Success(true))
            } else {
                emit(UiState.Error(UnknownError("User is not logged in.")))
            }
        } catch (e: Exception) {
            emit(UiState.Error(exception = e))
        }
    }

    override fun getUser(): UserRealm? {
        return realm.query<UserRealm>(query = "owner_id == $0", user?.id.toString()).first().find()
    }

    override fun signUpWithEmailAndPassword(userRealm: UserRealm): Flow<UiState<Boolean>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(userRealm: UserRealm) {
        realm.write {
            copyToRealm(userRealm)
        }
    }

    override fun getHomeProduct(): Flow<List<ProductRealm>> {
//        Log.d("TAG", "HomeScreen: homeProducts = valle")
        return realm.query<ProductRealm>().limit(6).asFlow().map { it.list }
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
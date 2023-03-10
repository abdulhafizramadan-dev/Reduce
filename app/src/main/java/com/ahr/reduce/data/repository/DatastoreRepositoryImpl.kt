package com.ahr.reduce.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.ahr.reduce.domain.repository.DatastoreRepository
import com.ahr.reduce.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constant.REDUCE_PREFERENCES)

class DatastoreRepositoryImpl(private val context: Context) : DatastoreRepository {

    private val dataStore get() = context.dataStore

    private object PreferenceKeys {
        val onBoardingState = booleanPreferencesKey(Constant.ON_BOARDING_PREFERENCES_KEY)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            val onBoardingState = preferences[PreferenceKeys.onBoardingState]
            onBoardingState ?: false
        }
    }

    override suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.edit {  preferences ->
            preferences[PreferenceKeys.onBoardingState] = state
        }
    }
}
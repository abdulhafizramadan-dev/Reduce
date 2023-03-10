package com.ahr.reduce.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    fun readOnBoardingState(): Flow<Boolean>
    suspend fun saveOnBoardingState(state: Boolean)
}
package com.ahr.reduce.di

import android.content.Context
import com.ahr.reduce.data.repository.DatastoreRepositoryImpl
import com.ahr.reduce.domain.repository.DatastoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DatastoreRepository {
        return DatastoreRepositoryImpl(context)
    }
}
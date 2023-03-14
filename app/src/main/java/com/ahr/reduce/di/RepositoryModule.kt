package com.ahr.reduce.di

import android.content.Context
import com.ahr.reduce.data.repository.DatastoreRepositoryImpl
import com.ahr.reduce.data.repository.RealmRepositoryImpl
import com.ahr.reduce.domain.repository.DatastoreRepository
import com.ahr.reduce.domain.repository.RealmRepository
import com.ahr.reduce.util.Constant.APP_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.mongodb.App
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

    @Provides
    @Singleton
    fun provideRealmApp(): App {
        return App.create(APP_ID)
    }

    @Provides
    @Singleton
    fun provideRealmRepository(
        realmApp: App
    ): RealmRepository {
        return RealmRepositoryImpl(realmApp = realmApp)
    }

}
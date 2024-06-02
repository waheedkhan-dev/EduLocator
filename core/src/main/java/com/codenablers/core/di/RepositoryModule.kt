package com.codenablers.core.di

import com.codenablers.core.data.datasource.local.dao.UniversitiesDao
import com.codenablers.core.data.datasource.remote.EduApi
import com.codenablers.core.data.repositories.UniversityRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    /**
     * Provides RemoteDataRepository for access api service method
     */
    @Singleton
    @Provides
    fun provideUniversityRepository(
        eduApi: EduApi,
        universitiesDao: UniversitiesDao
    ): UniversityRepositoryImpl {
        return UniversityRepositoryImpl(
            eduApi = eduApi,
            universitiesDao = universitiesDao
        )
    }
}
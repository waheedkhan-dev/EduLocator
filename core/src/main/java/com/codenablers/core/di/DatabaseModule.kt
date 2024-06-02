package com.codenablers.core.di

import android.content.Context
import androidx.room.Room
import com.codenablers.core.data.datasource.local.dao.UniversitiesDao
import com.codenablers.core.data.datasource.local.database.EduLocatorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideEduLocatorDb(@ApplicationContext context: Context): EduLocatorDatabase {
        return Room.databaseBuilder(
            context, EduLocatorDatabase::class.java,
            EduLocatorDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUniversitiesDao(eduLocatorDatabase: EduLocatorDatabase): UniversitiesDao {
        return eduLocatorDatabase.universitiesDao()
    }
}
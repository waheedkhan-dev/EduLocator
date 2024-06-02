package com.codenablers.core.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codenablers.core.data.datasource.local.converter.StringConverter
import com.codenablers.core.data.datasource.local.dao.UniversitiesDao
import com.codenablers.core.data.models.UniversitiesResponse


const val EDU_LOCATOR_DB = "edu_locator_db"

@Database(
    entities = [UniversitiesResponse.University::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringConverter::class)
abstract class EduLocatorDatabase : RoomDatabase() {
    abstract fun universitiesDao(): UniversitiesDao

    companion object {
        const val DATABASE_NAME = EDU_LOCATOR_DB
    }
}

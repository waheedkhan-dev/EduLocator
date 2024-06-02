package com.codenablers.core.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codenablers.core.data.models.UniversitiesResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UniversitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUniversities(universities: List<UniversitiesResponse.University>)

    @Query("SELECT *FROM universityTable where id = :universityId")
    fun getUniById(universityId: Int): Flow<UniversitiesResponse.University>

    @Query("SELECT *FROM universityTable")
    fun getUniversities(): List<UniversitiesResponse.University>

    @Query("DELETE FROM universityTable")
    fun deleteAll()

}
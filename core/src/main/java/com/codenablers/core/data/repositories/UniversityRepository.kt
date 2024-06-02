package com.codenablers.core.data.repositories

import com.codenablers.core.data.models.UniversitiesResponse
import com.codenablers.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    suspend fun getUniversities(): Flow<Result<List<UniversitiesResponse.University>>>
    suspend fun getUniversityById(id : Int): Flow<UniversitiesResponse.University>
}
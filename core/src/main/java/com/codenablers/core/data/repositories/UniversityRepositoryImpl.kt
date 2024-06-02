package com.codenablers.core.data.repositories

import com.codenablers.core.data.datasource.local.dao.UniversitiesDao
import com.codenablers.core.data.datasource.remote.EduApi
import com.codenablers.core.data.models.UniversitiesResponse
import com.codenablers.core.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val eduApi: EduApi,
    private val universitiesDao: UniversitiesDao
) :
    UniversityRepository {
    override suspend fun getUniversities(): Flow<Result<List<UniversitiesResponse.University>>> =
        flow {
            val universities = universitiesDao.getUniversities()
            // Emit the loading state if there is no record in database
            emit(if (universities.isNotEmpty()) Result.Success(universities) else Result.Loading)
            try {
                // Fetch universities from the API
                val response = eduApi.searchUniversities()
                if (response.isSuccessful) {
                    response.body()?.let {
                        universitiesDao.deleteAll()
                        universitiesDao.insertUniversities(it)
                        emit(Result.Success(universitiesDao.getUniversities()))
                    }
                } else {
                    emit(Result.Error(response.message().toString()))
                }

            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)


    override suspend fun getUniversityById(id: Int): Flow<UniversitiesResponse.University> =
        universitiesDao.getUniById(universityId = id)

}
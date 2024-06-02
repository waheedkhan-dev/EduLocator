package com.codenablers.core.data.datasource.remote

import com.codenablers.core.data.models.UniversitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface EduApi {
    @GET("search?country=United%20Arab%20Emirates")
    suspend fun searchUniversities(): Response<UniversitiesResponse>
}
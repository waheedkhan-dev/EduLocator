package com.codenablers.details.ui

import androidx.lifecycle.ViewModel
import com.codenablers.core.data.repositories.UniversityRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val universityRepositoryImpl: UniversityRepositoryImpl) :
    ViewModel() {

        // Get university by id
    suspend fun getUniversityById(universityId: Int) =
        universityRepositoryImpl.getUniversityById(id = universityId)
}
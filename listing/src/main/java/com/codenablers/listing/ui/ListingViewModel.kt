package com.codenablers.listing.ui

import androidx.lifecycle.ViewModel
import com.codenablers.core.data.repositories.UniversityRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val universityRepositoryImpl: UniversityRepositoryImpl) :
    ViewModel() {

    suspend fun getUniversity() =
        universityRepositoryImpl.getUniversities("United%20Arab%20Emirate")
}
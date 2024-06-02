package com.codenablers.core.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codenablers.core.data.datasource.local.Tables.UNIVERSITY_TABLE
import com.google.gson.annotations.SerializedName

class UniversitiesResponse : ArrayList<UniversitiesResponse.University>() {

    @Entity(tableName = UNIVERSITY_TABLE)
    data class University(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @SerializedName("alpha_two_code")
        val alphaTwoCode: String,
        val country: String,
        val domains: List<String>,
        val name: String,
        @SerializedName("state-province")
        val stateProvince: String?,
        @SerializedName("web_pages")
        val webPages: List<String>

    )
}
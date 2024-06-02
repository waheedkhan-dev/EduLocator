package com.codenablers.core.data.datasource.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return gson.toJson(list)
    }
}
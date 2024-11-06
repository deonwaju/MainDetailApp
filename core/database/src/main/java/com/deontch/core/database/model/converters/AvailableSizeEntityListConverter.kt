package com.deontch.core.database.model.converters

import androidx.room.TypeConverter
import com.deontch.core.database.model.AvailableSizeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AvailableSizeEntityListConverter {
    @TypeConverter
    fun fromJsonAvailableSizeList(availableSizes: List<AvailableSizeEntity>): String {
        val gson = Gson()
        return gson.toJson(availableSizes)
    }

    @TypeConverter
    fun toJsonAvailableSizeList(data: String): List<AvailableSizeEntity> {
        val gson = Gson()
        val listType = object : TypeToken<List<AvailableSizeEntity>>() {}.type
        return gson.fromJson(data, listType)
    }
}

package com.deontch.core.database.model.converters

import androidx.room.TypeConverter
import com.deontch.core.database.model.MediaEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MediaEntityListConverter {
    @TypeConverter
    fun fromJsonMediaList(media: List<MediaEntity>): String {
        val gson = Gson()
        return gson.toJson(media)
    }

    @TypeConverter
    fun toJsonMediaList(data: String): List<MediaEntity> {
        val gson = Gson()
        val listType = object : TypeToken<List<MediaEntity>>() {}.type
        return gson.fromJson(data, listType)
    }
}

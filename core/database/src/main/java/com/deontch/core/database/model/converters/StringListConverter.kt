package com.deontch.core.database.model.converters

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(",")
    }
}

package com.deontch.core.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.deontch.core.database.model.converters.AvailableSizeEntityListConverter
import com.deontch.core.database.model.converters.MediaEntityListConverter
import com.deontch.core.database.model.converters.StringListConverter

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey val id: Long,
    val objectID: String,
    val colour: String,
    val title: String,
    val price: Int,
    val description: String,
    val inStock: Boolean,
    val labels: String?,
    val sku: String,
    @Embedded val featuredMedia: FeaturedMediaEntity,
    @TypeConverters(MediaEntityListConverter::class) val media: List<MediaEntity>,
    @TypeConverters(AvailableSizeEntityListConverter::class) val availableSizes: List<AvailableSizeEntity>,
    @TypeConverters(StringListConverter::class) val sizeInStock: List<String>?,
)

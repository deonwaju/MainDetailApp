package com.deontch.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deontch.core.database.dao.ProductsDao
import com.deontch.core.database.model.AvailableSizeEntity
import com.deontch.core.database.model.FeaturedMediaEntity
import com.deontch.core.database.model.MediaEntity
import com.deontch.core.database.model.ProductsEntity
import com.deontch.core.database.model.converters.AvailableSizeEntityListConverter
import com.deontch.core.database.model.converters.MediaEntityListConverter
import com.deontch.core.database.model.converters.StringListConverter

@TypeConverters(
    StringListConverter::class,
    MediaEntityListConverter::class,
    AvailableSizeEntityListConverter::class
)
@Database(
    entities = [
        ProductsEntity::class,
        AvailableSizeEntity::class,
        FeaturedMediaEntity::class,
        MediaEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
}

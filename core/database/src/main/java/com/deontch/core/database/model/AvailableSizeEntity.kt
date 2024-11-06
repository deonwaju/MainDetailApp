package com.deontch.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "available_sizes")
data class AvailableSizeEntity(
    @PrimaryKey val id: Long,
    val inStock: Boolean,
    val inventoryQuantity: Int,
    val price: Int,
    val size: String,
    val sku: String,
)

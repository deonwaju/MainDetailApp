package com.deontch.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media")
data class MediaEntity(
    @PrimaryKey val id: Long,
    val adminGraphqlApiId: String,
    val createdAt: String,
    val height: Int,
    val position: Int,
    val productId: Long,
    val src: String,
    val updatedAt: String,
    val width: Int,
)

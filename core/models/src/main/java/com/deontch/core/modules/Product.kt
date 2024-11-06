package com.deontch.core.modules

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val title: String,
    val price: Int,
    val description: String,
    val inStock: Boolean,
    val labels: String?,
    val colour: String,
    val sku: String,
    val featuredMedia: FeaturedMedia,
    val media: List<Media>,
    val availableSizes: List<AvailableSize>,
) : Parcelable

@Parcelize
data class FeaturedMedia(
    val id: Long,
    val adminGraphqlApiId: String,
    val createdAt: String,
    val height: Int,
    val position: Int,
    val productId: Long,
    val src: String,
    val updatedAt: String,
    val width: Int,
) : Parcelable

@Parcelize
data class Media(
    val id: Long,
    val adminGraphqlApiId: String,
    val createdAt: String,
    val height: Int,
    val position: Int,
    val productId: Long,
    val src: String,
    val updatedAt: String,
) : Parcelable

@Parcelize
data class AvailableSize(
    val id: Long,
    val inStock: Boolean,
    val inventoryQuantity: Int,
    val price: Int,
    val size: String,
    val sku: String,
) : Parcelable

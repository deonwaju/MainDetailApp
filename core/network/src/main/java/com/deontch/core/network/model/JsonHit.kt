package com.deontch.core.network.model

data class JsonHit(
    val id: Long,
    val objectID: String,
    val availableSizes: List<JsonAvailableSize>,
    val colour: String,
    val price: Int,
    val description: String,
    val featuredMedia: JsonFeaturedMedia,
    val inStock: Boolean,
    val labels: Any,
    val media: List<JsonMedia>,
    val sizeInStock: List<String>?,
    val sku: String,
    val title: String,
)

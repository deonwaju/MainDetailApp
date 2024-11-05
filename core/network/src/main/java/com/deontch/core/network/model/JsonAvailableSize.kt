package com.deontch.core.network.model

data class JsonAvailableSize(
    val id: Long,
    val inStock: Boolean,
    val inventoryQuantity: Int,
    val price: Int,
    val size: String,
    val sku: String
)

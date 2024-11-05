package com.deontch.core.network.model

data class JsonFeaturedMedia(
    val id: Long,
    val admin_graphql_api_id: String,
    val alt: Any,
    val created_at: String,
    val height: Int,
    val position: Int,
    val product_id: Long,
    val src: String,
    val updated_at: String,
    val variant_ids: List<Any>,
    val width: Int
)

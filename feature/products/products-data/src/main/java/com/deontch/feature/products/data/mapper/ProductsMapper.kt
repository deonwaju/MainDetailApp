package com.deontch.feature.products.data.mapper

import com.deontch.core.database.model.AvailableSizeEntity
import com.deontch.core.database.model.FeaturedMediaEntity
import com.deontch.core.database.model.MediaEntity
import com.deontch.core.database.model.ProductsEntity
import com.deontch.core.modules.AvailableSize
import com.deontch.core.modules.FeaturedMedia
import com.deontch.core.modules.Media
import com.deontch.core.modules.Product
import com.deontch.core.network.model.JsonAvailableSize
import com.deontch.core.network.model.JsonFeaturedMedia
import com.deontch.core.network.model.JsonHit
import com.deontch.core.network.model.JsonMedia
import javax.inject.Inject

class ProductsMapper @Inject constructor() {

    fun mapToUIModel(productsEntity: ProductsEntity): Product {
        return Product(
            id = productsEntity.id,
            title = productsEntity.title,
            price = productsEntity.price,
            description = productsEntity.description,
            inStock = productsEntity.inStock,
            labels = productsEntity.labels,
            sku = productsEntity.sku,
            featuredMedia = mapFeaturedMedia(productsEntity.featuredMedia),
            media = productsEntity.media.map { mapMedia(it) },
            availableSizes = productsEntity.availableSizes.map { mapAvailableSize(it) }
        )
    }

    fun mapToEntity(jsonHit: JsonHit): ProductsEntity {
        return ProductsEntity(
            id = jsonHit.id,
            objectID = jsonHit.objectID,
            colour = jsonHit.colour,
            title = jsonHit.title,
            price = jsonHit.price,
            description = jsonHit.description,
            inStock = jsonHit.inStock,
            labels = mapLabels(jsonHit.labels),
            sku = jsonHit.sku,
            featuredMedia = mapFeaturedMedia(jsonHit.featuredMedia),
            media = jsonHit.media.map { mapMedia(it) },
            availableSizes = jsonHit.availableSizes.map { mapAvailableSize(it) },
            sizeInStock = jsonHit.sizeInStock
        )
    }

    private fun mapFeaturedMedia(featuredMediaEntity: FeaturedMediaEntity): FeaturedMedia {
        return FeaturedMedia(
            id = featuredMediaEntity._id,
            adminGraphqlApiId = featuredMediaEntity.adminGraphqlApiId,
            createdAt = featuredMediaEntity.createdAt,
            height = featuredMediaEntity.height,
            position = featuredMediaEntity.position,
            productId = featuredMediaEntity.productId,
            src = featuredMediaEntity.src,
            updatedAt = featuredMediaEntity.updatedAt,
            width = featuredMediaEntity.width
        )
    }

    private fun mapMedia(mediaEntity: MediaEntity): Media {
        return Media(
            id = mediaEntity.id,
            adminGraphqlApiId = mediaEntity.adminGraphqlApiId,
            createdAt = mediaEntity.createdAt,
            height = mediaEntity.height,
            position = mediaEntity.position,
            productId = mediaEntity.productId,
            src = mediaEntity.src,
            updatedAt = mediaEntity.updatedAt
        )
    }

    private fun mapAvailableSize(availableSizeEntity: AvailableSizeEntity): AvailableSize {
        return AvailableSize(
            id = availableSizeEntity.id,
            inStock = availableSizeEntity.inStock,
            inventoryQuantity = availableSizeEntity.inventoryQuantity,
            price = availableSizeEntity.price,
            size = availableSizeEntity.size,
            sku = availableSizeEntity.sku
        )
    }
    private fun mapLabels(labels: Any?): String? {
        return when (labels) {
            is String -> labels
            is List<*> -> labels.filterIsInstance<String>().joinToString(", ")
            else -> null
        }
    }

    private fun mapFeaturedMedia(jsonFeaturedMedia: JsonFeaturedMedia): FeaturedMediaEntity {
        return FeaturedMediaEntity(
            _id = jsonFeaturedMedia.id,
            adminGraphqlApiId = jsonFeaturedMedia.admin_graphql_api_id,
            createdAt = jsonFeaturedMedia.created_at,
            height = jsonFeaturedMedia.height,
            position = jsonFeaturedMedia.position,
            productId = jsonFeaturedMedia.product_id,
            src = jsonFeaturedMedia.src,
            updatedAt = jsonFeaturedMedia.updated_at,
            width = jsonFeaturedMedia.width
        )
    }

    private fun mapMedia(jsonMedia: JsonMedia): MediaEntity {
        return MediaEntity(
            adminGraphqlApiId = jsonMedia.admin_graphql_api_id,
            createdAt = jsonMedia.created_at,
            height = jsonMedia.height,
            id = jsonMedia.id,
            position = jsonMedia.position,
            productId = jsonMedia.product_id,
            src = jsonMedia.src,
            updatedAt = jsonMedia.updated_at,
            width = jsonMedia.width
        )
    }

    private fun mapAvailableSize(jsonAvailableSize: JsonAvailableSize): AvailableSizeEntity {
        return AvailableSizeEntity(
            id = jsonAvailableSize.id,
            inStock = jsonAvailableSize.inStock,
            inventoryQuantity = jsonAvailableSize.inventoryQuantity,
            price = jsonAvailableSize.price,
            size = jsonAvailableSize.size,
            sku = jsonAvailableSize.sku
        )
    }
}

package com.deontch.feature.products.data

import com.deontch.core.database.model.AvailableSizeEntity
import com.deontch.core.database.model.FeaturedMediaEntity
import com.deontch.core.database.model.MediaEntity
import com.deontch.core.database.model.ProductsEntity
import com.deontch.core.network.model.JsonAvailableSize
import com.deontch.core.network.model.JsonFeaturedMedia
import com.deontch.core.network.model.JsonHit
import com.deontch.core.network.model.JsonMedia

class ProductsMapper {

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

    private fun mapLabels(labels: Any?): String? {
        return when (labels) {
            is String -> labels
            is List<*> -> labels.filterIsInstance<String>().joinToString(", ")
            else -> null
        }
    }

    private fun mapFeaturedMedia(jsonFeaturedMedia: JsonFeaturedMedia): FeaturedMediaEntity {
        return FeaturedMediaEntity(
            id = jsonFeaturedMedia.id,
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

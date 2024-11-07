package com.deontch.feature.products.data.mapper

import com.deontch.core.common.test.DummyData.jsonProduct
import com.deontch.core.common.test.DummyData.productEntity
import com.deontch.core.database.model.AvailableSizeEntity
import com.deontch.core.database.model.FeaturedMediaEntity
import com.deontch.core.database.model.MediaEntity
import com.deontch.core.modules.AvailableSize
import com.deontch.core.modules.FeaturedMedia
import com.deontch.core.modules.Media
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ProductsMapperTest {
    private val mapper = ProductsMapper()

    @Test
    fun `given ProductsEntity when mapToUIModel is called, then it should return valid Product`() {
        val result = mapper.mapToUIModel(productEntity)

        with(result) {
            assertThat(id).isEqualTo(productEntity.id)
            assertThat(title).isEqualTo(productEntity.title)
            assertThat(price).isEqualTo(productEntity.price)
            assertThat(description).isEqualTo(productEntity.description)
            assertThat(inStock).isEqualTo(productEntity.inStock)
            assertThat(labels).isEqualTo(productEntity.labels)
            assertThat(sku).isEqualTo(productEntity.sku)
            assertThat(colour).isEqualTo(productEntity.colour)
            assertThat(featuredMedia).isEqualTo(mapFeaturedMedia(productEntity.featuredMedia))
            assertThat(media).isEqualTo(productEntity.media.map { mapMedia(it) })
            assertThat(availableSizes).isEqualTo(productEntity.availableSizes.map {
                mapAvailableSize(it)
            })
            assertThat(sizeInStock).isEqualTo(productEntity.sizeInStock)
        }
    }

    @Test
    fun `given JsonHit when mapToEntity is called, then it should return valid ProductsEntity`() {
        val result = mapper.mapToEntity(jsonProduct)

        with(result) {
            assertThat(id).isEqualTo(jsonProduct.id)
            assertThat(objectID).isEqualTo(jsonProduct.objectID)
            assertThat(colour).isEqualTo(jsonProduct.colour)
            assertThat(price).isEqualTo(jsonProduct.price)
            assertThat(description).isEqualTo(jsonProduct.description)
            assertThat(inStock).isEqualTo(jsonProduct.inStock)
            assertThat(labels).isEqualTo(mapLabels(jsonProduct.labels))
            assertThat(sku).isEqualTo(jsonProduct.sku)
            assertThat(featuredMedia).isEqualTo(
                jsonProduct.featuredMedia.let {
                    FeaturedMediaEntity(
                        _id = it.id,
                        adminGraphqlApiId = it.admin_graphql_api_id,
                        createdAt = it.created_at,
                        height = it.height,
                        position = it.position,
                        productId = it.product_id,
                        src = it.src,
                        updatedAt = it.updated_at,
                        width = it.width
                    )
                }
            )
            assertThat(media).isEqualTo(
                jsonProduct.media.map {
                    MediaEntity(
                        id = it.id,
                        adminGraphqlApiId = it.admin_graphql_api_id,
                        createdAt = it.created_at,
                        height = it.height,
                        position = it.position,
                        productId = it.product_id,
                        src = it.src,
                        updatedAt = it.updated_at,
                        width = it.width
                    )
                }
            )
            assertThat(availableSizes).isEqualTo(
                jsonProduct.availableSizes.map {
                    AvailableSizeEntity(
                        id = it.id,
                        inStock = it.inStock,
                        inventoryQuantity = it.inventoryQuantity,
                        price = it.price,
                        size = it.size,
                        sku = it.sku
                    )
                }
            )
            assertThat(sizeInStock).isEqualTo(jsonProduct.sizeInStock)
        }
    }

    // Helper method to verify FeaturedMedia mapping
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

    // Helper method to verify Media mapping
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

    // Helper method to verify AvailableSize mapping
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

    // Helper method to verify Labels mapping
    private fun mapLabels(labels: Any?): String? {
        return when (labels) {
            is String -> labels
            is List<*> -> labels.filterIsInstance<String>().joinToString(", ")
            else -> null
        }
    }
}
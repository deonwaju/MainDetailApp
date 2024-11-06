package com.deontch.core.common.test

import com.deontch.core.modules.AvailableSize
import com.deontch.core.modules.FeaturedMedia
import com.deontch.core.modules.Media
import com.deontch.core.modules.Products

object DummyData {

    fun getDummyProducts(): List<Products> {
        return listOf(
            getDummyProduct1(),
            getDummyProduct2(),
            getDummyProduct3()
        )
    }

    private fun getDummyProduct1(): Products {
        return Products(
            id = 1L,
            title = "Product 1",
            price = 1499, // 14.99 in cents
            description = "This is the description for Product 1.",
            inStock = true,
            labels = "Sale, Popular",
            sku = "PROD001",
            featuredMedia = getDummyFeaturedMedia(101L),
            media = listOf(getDummyMedia(201L), getDummyMedia(202L)),
            availableSizes = listOf(getDummyAvailableSize("S"), getDummyAvailableSize("M"))
        )
    }

    private fun getDummyProduct2(): Products {
        return Products(
            id = 2L,
            title = "Product 2",
            price = 2599, // 25.99 in cents
            description = "This is the description for Product 2.",
            inStock = false,
            labels = "New Arrival",
            sku = "PROD002",
            featuredMedia = getDummyFeaturedMedia(102L),
            media = listOf(getDummyMedia(203L), getDummyMedia(204L)),
            availableSizes = listOf(getDummyAvailableSize("L"), getDummyAvailableSize("XL"))
        )
    }

    private fun getDummyProduct3(): Products {
        return Products(
            id = 3L,
            title = "Product 3",
            price = 1999, // 19.99 in cents
            description = "This is the description for Product 3.",
            inStock = true,
            labels = "Limited Edition",
            sku = "PROD003",
            featuredMedia = getDummyFeaturedMedia(103L),
            media = listOf(getDummyMedia(205L), getDummyMedia(206L)),
            availableSizes = listOf(getDummyAvailableSize("M"), getDummyAvailableSize("L"))
        )
    }

    private fun getDummyFeaturedMedia(id: Long): FeaturedMedia {
        return FeaturedMedia(
            id = id,
            adminGraphqlApiId = "admin_$id",
            createdAt = "2024-11-01T12:00:00Z",
            height = 500,
            position = 1,
            productId = id,
            src = "https://example.com/media/featured-image-$id.jpg",
            updatedAt = "2024-11-02T12:00:00Z",
            width = 500
        )
    }

    private fun getDummyMedia(id: Long): Media {
        return Media(
            id = id,
            adminGraphqlApiId = "media_$id",
            createdAt = "2024-11-01T12:00:00Z",
            height = 400,
            position = 1,
            productId = id,
            src = "https://example.com/media/image-$id.jpg",
            updatedAt = "2024-11-02T12:00:00Z"
        )
    }

    private fun getDummyAvailableSize(size: String): AvailableSize {
        return AvailableSize(
            id = 301L,
            inStock = true,
            inventoryQuantity = 50,
            price = 1999, // 19.99 in cents
            size = size,
            sku = "SIZE-$size"
        )
    }
}

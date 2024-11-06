package com.deontch.core.common.test

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

object DummyData {
    // Dummy JSON data representing a product from the API
    val jsonProduct = JsonHit(
        id = 2,
        objectID = "124",
        availableSizes = listOf(JsonAvailableSize(2, true, 200, 600, "M", "SKU457")),
        colour = "Blue",
        price = 1200,
        description = "Another Product Description",
        featuredMedia = JsonFeaturedMedia(
            2,
            "GraphqlId2",
            "2024-02-01",
            450,
            2,
            2,
            "url2",
            "2024-02-01",
            350
        ),
        inStock = false,
        labels = "Clearance, New",
        media = listOf(
            JsonMedia(
                "GraphqlId2",
                "3",
                "2024-02-01",
                450,
                2,
                2,
                2,
                "url2",
                "2024-02-01",
                listOf(),
                350,
            )
        ),
        sizeInStock = listOf("M", "L"),
        sku = "SKU124",
        title = "Product 2"
    )

    // Entity data representation (similar to database model)
    val productEntity = ProductsEntity(
        id = 2,
        title = "Product 2",
        price = 1200,
        description = "Another Product Description",
        inStock = false,
        labels = "Clearance, New",
        sku = "SKU124",
        colour = "Blue",
        featuredMedia = FeaturedMediaEntity(
            2,
            "GraphqlId2",
            "2024-02-01",
            450,
            2,
            2,
            "url2",
            "2024-02-01",
            350
        ),
        media = listOf(
            MediaEntity(
                2,
                "GraphqlId2",
                "2024-02-01",
                450,
                2,
                2,
                "url2",
                "2024-02-01",
                350
            )
        ),
        availableSizes = listOf(AvailableSizeEntity(2, true, 150, 600, "M", "SKU457")),
        sizeInStock = listOf("M", "L"),
        objectID = "124"
    )

    // Function to return a list of products for testing
    fun getDummyProducts(): List<Product> {
        return listOf(
            getDummyProduct1(),
            getDummyProduct2()
        )
    }

    // Individual dummy product
    private fun getDummyProduct1(): Product {
        return Product(
            id = 1L,
            title = "Product 1",
            price = 1499, // 14.99 in cents
            description = "This is the description for Product 1.",
            inStock = true,
            labels = "Sale, Popular",
            sku = "PROD001",
            featuredMedia = getDummyFeaturedMedia(101L),
            media = listOf(getDummyMedia(201L), getDummyMedia(202L)),
            availableSizes = listOf(getDummyAvailableSize("S"), getDummyAvailableSize("M")),
            sizeInStock = listOf("S", "M"),
            colour = "Black"
        )
    }

    private fun getDummyProduct2(): Product {
        return Product(
            id = 2L,
            title = "Product 2",
            price = 1299, // 12.99 in cents
            description = "This is the description for Product 2.",
            inStock = false,
            labels = "Clearance, New",
            sku = "PROD002",
            featuredMedia = getDummyFeaturedMedia(102L),
            media = listOf(getDummyMedia(203L), getDummyMedia(204L)),
            availableSizes = listOf(getDummyAvailableSize("M"), getDummyAvailableSize("L")),
            sizeInStock = listOf("M", "L"),
            colour = "Blue"
        )
    }

    // Helper function to create featured media
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

    // Helper function to create media
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

    // Helper function to create available sizes
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

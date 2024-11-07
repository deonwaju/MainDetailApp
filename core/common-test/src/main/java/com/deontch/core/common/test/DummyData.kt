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
            mediaId = 2,
            adminGraphqlApiId = "GraphqlId2",
            createdAt = "2024-02-01",
            height = 450,
            position = 2,
            productId = 2,
            src = "url2",
            updatedAt = "2024-02-01",
            width = 350
        ),
        media = listOf(
            MediaEntity(
                id = 2,
                adminGraphqlApiId = "GraphqlId2",
                createdAt = "2024-02-01",
                height = 450,
                position = 2,
                productId = 2,
                src = "url2",
                updatedAt = "2024-02-01",
                width = 350
            )
        ),
        availableSizes = listOf(AvailableSizeEntity(2, true, 150, 600, "M", "SKU457")),
        sizeInStock = listOf("M", "L"),
        objectID = "124"
    )

    fun getDummyProducts(): List<Product> {
        return listOf(
            getDummyProduct1()
        )
    }

    fun getDummyProduct1(): Product {
        return Product(
            id = 2L,
            title = "Product 2",
            price = 1200,
            description = "Another Product Description",
            inStock = false,
            labels = "Clearance, New",
            sku = "SKU124",
            featuredMedia = FeaturedMedia(
                id = 2L,
                adminGraphqlApiId = "GraphqlId2",
                createdAt = "2024-02-01",
                height = 450,
                position = 2,
                productId = 2L,
                src = "url2",
                updatedAt = "2024-02-01",
                width = 350
            ),
            media = listOf(
                Media(
                    id = 2L,
                    adminGraphqlApiId = "GraphqlId2",
                    createdAt = "2024-02-01",
                    height = 450,
                    position = 2,
                    productId = 2L,
                    src = "url2",
                    updatedAt = "2024-02-01",
                )
            ),
            availableSizes = listOf(
                AvailableSize(
                    id = 2L,
                    inStock = true,
                    inventoryQuantity = 150,
                    price = 600,
                    size = "M",
                    sku = "SKU457"
                )
            ),
            sizeInStock = listOf("M", "L"),
            colour = "Blue"
        )
    }
    fun getDummyProduct2(): Product {
        return Product(
            id = 2L,
            title = "Product 3",
            price = 1200,
            description = "Another Product Description",
            inStock = false,
            labels = "Clearance, New",
            sku = "SKU124",
            featuredMedia = FeaturedMedia(
                id = 2L,
                adminGraphqlApiId = "GraphqlId2",
                createdAt = "2024-02-01",
                height = 450,
                position = 2,
                productId = 2L,
                src = "url2",
                updatedAt = "2024-02-01",
                width = 350
            ),
            media = listOf(
                Media(
                    id = 2L,
                    adminGraphqlApiId = "GraphqlId2",
                    createdAt = "2024-02-01",
                    height = 450,
                    position = 2,
                    productId = 2L,
                    src = "url2",
                    updatedAt = "2024-02-01",
                )
            ),
            availableSizes = listOf(
                AvailableSize(
                    id = 2L,
                    inStock = true,
                    inventoryQuantity = 150,
                    price = 600,
                    size = "M",
                    sku = "SKU457"
                )
            ),
            sizeInStock = listOf("M", "L"),
            colour = "Blue"
        )
    }
    fun getDummyProduct(): Product {
        return Product(
            id = 2L,
            title = "Product 4",
            price = 1200,
            description = "Another Product Description",
            inStock = false,
            labels = "Clearance, New",
            sku = "SKU124",
            featuredMedia = FeaturedMedia(
                id = 2L,
                adminGraphqlApiId = "GraphqlId2",
                createdAt = "2024-02-01",
                height = 450,
                position = 2,
                productId = 2L,
                src = "url2",
                updatedAt = "2024-02-01",
                width = 350
            ),
            media = listOf(
                Media(
                    id = 2L,
                    adminGraphqlApiId = "GraphqlId2",
                    createdAt = "2024-02-01",
                    height = 450,
                    position = 2,
                    productId = 2L,
                    src = "url2",
                    updatedAt = "2024-02-01",
                )
            ),
            availableSizes = listOf(
                AvailableSize(
                    id = 2L,
                    inStock = true,
                    inventoryQuantity = 150,
                    price = 600,
                    size = "M",
                    sku = "SKU457"
                )
            ),
            sizeInStock = listOf("M", "L"),
            colour = "Blue"
        )
    }
}

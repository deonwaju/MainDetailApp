package com.deontch.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.deontch.core.database.model.ProductsEntity

@Dao
interface ProductsDao {

    // Insert a single Product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductsEntity)

    // Insert multiple Products
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductsEntity>)

    @Update
    suspend fun updateProduct(product: ProductsEntity)

    @Delete
    suspend fun deleteProduct(product: ProductsEntity)

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("SELECT * FROM products")
    suspend fun getAllProducts(): List<ProductsEntity>

    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Long): ProductsEntity?

    @Query("SELECT * FROM products WHERE objectID = :objectID")
    suspend fun getProductByObjectID(objectID: String): ProductsEntity?

    @Query("SELECT * FROM products WHERE inStock = 1")
    suspend fun getInStockProducts(): List<ProductsEntity>

    @Query("SELECT * FROM products WHERE sku = :sku")
    suspend fun getProductBySku(sku: String): ProductsEntity?

    @Query("SELECT * FROM products WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun searchProductsByName(searchQuery: String): List<ProductsEntity>
}

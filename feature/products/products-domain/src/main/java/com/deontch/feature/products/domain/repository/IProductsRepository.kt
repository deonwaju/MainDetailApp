package com.deontch.feature.products.domain.repository

import com.deontch.core.modules.Product
import kotlinx.coroutines.flow.Flow

interface IProductsRepository {
    fun getProducts(): Flow<List<Product>>
    fun getProductDetailById(id: Long): Flow<Product>
}

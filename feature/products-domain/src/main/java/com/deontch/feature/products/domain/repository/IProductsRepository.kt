package com.deontch.feature.products.domain.repository

import com.deontch.core.modules.Products
import kotlinx.coroutines.flow.Flow

interface IProductsRepository {
    fun getProducts(): Flow<List<Products>>
    fun getProductDetailById(id: Int): Flow<Products>
}

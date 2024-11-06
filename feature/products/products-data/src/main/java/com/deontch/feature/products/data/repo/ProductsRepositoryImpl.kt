package com.deontch.feature.products.data.repo

import com.deontch.core.database.dao.ProductsDao
import com.deontch.core.modules.Product
import com.deontch.core.network.ProductsAPIService
import com.deontch.feature.products.data.mapper.ProductsMapper
import com.deontch.feature.products.domain.repository.IProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsDao: ProductsDao,
    private val productsApi: ProductsAPIService,
    private val mapper: ProductsMapper,
): IProductsRepository {
    override fun getProducts(): Flow<List<Product>> = productsDao.getAllProducts()
        .onStart {
            productsApi.getProducts().also { apiModels ->
                productsDao.insertProducts(apiModels.hits.map(mapper::mapToEntity))
            }
        }.map { products ->
            products.map {
                mapper.mapToUIModel(it)
            }
        }

    override fun getProductDetailById(id: Long): Flow<Product> =
        productsDao.getProductById(id).map { product ->
            mapper.mapToUIModel(product)
        }
}

package com.deontch.feature.products.data.di

import com.deontch.core.database.dao.ProductsDao
import com.deontch.core.network.ProductsAPIService
import com.deontch.feature.products.data.mapper.ProductsMapper
import com.deontch.feature.products.data.repo.ProductsRepositoryImpl
import com.deontch.feature.products.domain.repository.IProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsDataModule {

    @Provides
    @Singleton
    fun provideProductsRepository(
        productsDao: ProductsDao,
        productsAPIService: ProductsAPIService,
        mapper: ProductsMapper
    ): IProductsRepository = ProductsRepositoryImpl(
        productsDao,
        productsAPIService,
        mapper
    )
}
package com.deontch.core.network

import com.deontch.core.network.model.JsonProductsResponse
import retrofit2.http.GET

interface ProductsAPIService {

    @GET("training/mock-product-responses/algolia-example-payload.json")
    suspend fun getProducts(): JsonProductsResponse
}
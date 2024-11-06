package com.deontch.feature.products.domain.usecases

import com.deontch.core.common.domain.usecases.FlowUseCase
import com.deontch.core.common.domain.usecases.NoParamsException
import com.deontch.core.common.domain.utils.DispatcherProvider
import com.deontch.core.modules.Product
import com.deontch.feature.products.domain.repository.IProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productsRepository: IProductsRepository,
    dispatcherProvider: DispatcherProvider,
): FlowUseCase<Long, Product>(dispatcherProvider) {
    override fun build(params: Long?): Flow<Product> {
        params ?: throw NoParamsException()
        return productsRepository.getProductDetailById(params)
    }
}

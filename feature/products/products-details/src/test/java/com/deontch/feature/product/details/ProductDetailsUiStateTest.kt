package com.deontch.feature.product.details

import com.deontch.core.common.test.DummyData
import com.deontch.feature.product.details.presentation.ProductDetailsUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDetailsUiStateTest {

    @Test
    fun `Success state should hold correct product data`() {
        val product = DummyData.getDummyProduct1()
        
        val successState = ProductDetailsUiState.Success(product)

        assertEquals(product, successState.data)
    }
}

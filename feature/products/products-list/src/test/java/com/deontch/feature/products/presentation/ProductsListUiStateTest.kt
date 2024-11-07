package com.deontch.feature.products.presentation

import com.deontch.core.common.test.DummyData
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ProductsListUiStateTest {

    @Test
    fun `Success state should hold correct list of products`() {
        val testProducts = DummyData.getDummyProducts()

        val successState = ProductsListUiState.Success(testProducts)

        assertEquals(testProducts, successState.products)
    }

    @Test
    fun `Error state should hold correct error message`() {
        val errorMessage = "An error occurred"

        val errorState = ProductsListUiState.Error(errorMessage)

        assertEquals(errorMessage, errorState.message)
    }
}

package com.example.foodies.screens.product

import com.example.foodies.data.models.Product

sealed class ProductState {
    data object Loading : ProductState()
    data class Error(val message: String) : ProductState()
    data class Content(
        var product: Product
    ) : ProductState()
}
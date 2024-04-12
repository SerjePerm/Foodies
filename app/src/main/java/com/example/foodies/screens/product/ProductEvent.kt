package com.example.foodies.screens.product

import com.example.foodies.data.models.Product

sealed class ProductEvent {
    data class CartIncrease(val product: Product) : ProductEvent()
    data class CartDecrease(val product: Product) : ProductEvent()
}
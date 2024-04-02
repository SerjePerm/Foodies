package com.example.foodies.screens.cart

import com.example.foodies.data.models.Product

sealed class CartEvent {
    data class Increase(val product: Product) : CartEvent()
    data class Decrease(val product: Product) : CartEvent()
}
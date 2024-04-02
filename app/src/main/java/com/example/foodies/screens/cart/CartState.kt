package com.example.foodies.screens.cart

import com.example.foodies.data.models.Category
import com.example.foodies.data.models.Product
import com.example.foodies.data.models.Tag

sealed class CartState {
    data object Loading : CartState()
    data class Error(val message: String) : CartState()
    data class Content(var cartList: List<Product> = emptyList()) : CartState()
}
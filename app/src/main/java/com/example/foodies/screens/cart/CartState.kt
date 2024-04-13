package com.example.foodies.screens.cart

sealed class CartState {
    data object Loading : CartState()
    data class Error(val message: String) : CartState()
    data class Content(val data: String) : CartState()
}
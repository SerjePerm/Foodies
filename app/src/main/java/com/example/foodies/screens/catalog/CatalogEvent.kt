package com.example.foodies.screens.catalog

import com.example.foodies.data.models.Product

sealed class CatalogEvent {
    data class AddToCart(val product: Product) : CatalogEvent()
    data class DelFromCart(val product: Product) : CatalogEvent()
}
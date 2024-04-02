package com.example.foodies.screens.catalog

import com.example.foodies.data.models.ProductDto

sealed class CatalogEvent {
    data class AddToCart(val product: ProductDto) : CatalogEvent()
    data class DelFromCart(val product: ProductDto) : CatalogEvent()
}
package com.example.foodies.screens.catalog

import com.example.foodies.data.models.Product

sealed class CatalogEvent {
    data class CartIncrease(val product: Product) : CatalogEvent()
    data class CartDecrease(val product: Product) : CatalogEvent()
    data class SelectCategory(val categoryId: Int) : CatalogEvent()
}
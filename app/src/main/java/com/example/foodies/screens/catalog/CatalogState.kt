package com.example.foodies.screens.catalog

import com.example.foodies.data.models.Category
import com.example.foodies.data.models.Product
import com.example.foodies.data.models.Tag

sealed class CatalogState {
    data object Loading : CatalogState()
    data class Error(val message: String) : CatalogState()
    data class Content(
        var categories: List<Category> = emptyList(),
        var tags: List<Tag> = emptyList(),
        var products: List<Product> = emptyList(),
        var selectedProducts: List<Product> = emptyList(),
        var selectedCategory: Int = -1
    ) : CatalogState()
}
package com.example.foodies.screens.catalog

import com.example.foodies.data.models.Category

sealed class CatalogState {
    data object Loading : CatalogState()
    data class Error(val message: String) : CatalogState()
    data class Content(
        var categories: List<Category> = emptyList()
    ) : CatalogState()
}
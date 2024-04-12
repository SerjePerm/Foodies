package com.example.foodies.data.models

data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Int,
    val priceOld: Int?,
    val measure: Int,
    val measureUnit: String,
    val energyPer100grams: Float,
    val proteinsPer100grams: Float,
    val fatsPer100Grams: Float,
    val carbohydratesPer100Grams: Float,
    val tagIds: List<String>,
    val count: Int
)
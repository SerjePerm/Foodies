package com.example.foodies.data.models

data class Product(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val image: String,
    val priceCurrent: Int,
    val priceOld: String?,
    val measure: Int,
    val measureUnit: String,
    val energyPer100grams: Double,
    val proteinsPer100grams: Double,
    val fatsPer100Grams: Double,
    val carbohydratesPer100Grams: Double,
    val tagIds: List<String>,
    val count: Int
)
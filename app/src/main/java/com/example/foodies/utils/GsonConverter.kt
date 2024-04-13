package com.example.foodies.utils

import com.example.foodies.data.models.Product
import com.google.gson.Gson

fun productToJson(product: Product): String {
    return Gson().toJson(product)
}

fun jsonToProduct(productStr: String): Product {
    return try {
        Gson().fromJson(productStr, Product::class.java)
    } catch (e: Exception) {
        Product(
            id = 0,
            categoryId = 0,
            name = "Error!",
            description = "",
            image = "",
            priceCurrent = 0,
            priceOld = null,
            measure = 0,
            measureUnit = "",
            energyPer100grams = 0.0f,
            proteinsPer100grams = 0.0f,
            fatsPer100Grams = 0.0f,
            carbohydratesPer100Grams = 0.0f,
            tagIds = listOf(),
            count = 0
        )
    }
}
package com.example.foodies.utils

import com.example.foodies.data.models.Product
import com.google.gson.Gson
import java.lang.Exception

fun productToJson(product: Product): String {
    return Gson().toJson(product)
}

fun jsonToProduct(productStr: String): Product {
    return try {
        Gson().fromJson(productStr, Product::class.java)
    } catch (e: Exception) {
        Product(0, 0, "Error!", "", "", 0, "", 0, "", 0.0, 0.0, 0.0, 0.0, listOf(""), 0)
    }
}
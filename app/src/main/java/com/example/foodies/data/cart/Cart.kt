package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.Flow

interface Cart {
    fun add(product: Product)
    fun del(product: Product)
    fun getAll(): List<Product>
    suspend fun get(): Flow<List<Product>>
    fun getCount(id: Int): Int
}
package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Cart {
    val _orderFlow: MutableStateFlow<List<String>>
    fun add(product: Product)
    fun del(product: Product)
    /*
    fun getAll(): List<Product>
    suspend fun get(): Flow<List<Product>>
    fun getCount(id: Int): Int

     */
}
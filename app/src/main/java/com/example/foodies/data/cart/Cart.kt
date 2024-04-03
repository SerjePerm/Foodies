package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.StateFlow

interface Cart {
    val order: StateFlow<List<Product>>
    fun increase(product: Product)
    fun decrease(product: Product)
    fun getCount(product: Product): Int
}
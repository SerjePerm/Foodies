package com.example.foodies.data.cart

import com.example.foodies.data.models.ProductDto

interface Cart {
    fun add(product: ProductDto)
    fun del(product: ProductDto)
}
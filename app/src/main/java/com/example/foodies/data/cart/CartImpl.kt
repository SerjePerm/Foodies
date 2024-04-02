package com.example.foodies.data.cart

import com.example.foodies.data.models.ProductDto

class CartImpl : Cart {

    private val cart: List<ProductDto> = emptyList()

    override fun add(product: ProductDto) {
        println("adding to cart ${product.name}")
    }

    override fun del(product: ProductDto) {
        println("deleting from cart ${product.name}")
    }

}
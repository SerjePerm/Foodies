package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

class CartImpl : Cart {

    private val _cart: MutableList<Product> = mutableListOf()

    override fun add(product: Product) {
        val newCount = getCount(product.id) + 1
        val newProduct = product.copy(count = newCount)
        _cart.removeAll{ it.id == product.id }
        _cart.add(newProduct)
    }

    override fun del(product: Product) {
        //TODO
    }

    override fun getAll(): List<Product> {
        return _cart
    }

    override suspend fun get(): Flow<List<Product>> {
        val result : Flow<List<Product>> = flow {
            while (true) {
                _cart.asFlow()
            }
        }
        return result
    }

    override fun getCount(id: Int): Int {
        val product = _cart.find { it.id == id }
        return product?.count ?: 0
    }


}
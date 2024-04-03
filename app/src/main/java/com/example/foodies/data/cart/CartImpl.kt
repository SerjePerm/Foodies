package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class CartImpl : Cart {

    private val _order: MutableList<String> = mutableListOf()
    override val _orderFlow: MutableStateFlow<List<String>> =  MutableStateFlow(listOf())

/*
    private val _order: MutableStateFlow<MutableList<String>> = MutableStateFlow(mutableListOf("init list"))
    override val order: StateFlow<List<String>> = _order

 */

    override fun add(product: Product) {
        _order.add("added string")
        _orderFlow.update { _order }

    }

    override fun del(product: Product) {
        _order.add("minus string")
        _orderFlow.update { _order }
    }

    /*
    override fun getAll(): List<Product> {
        return emptyList()
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

     */


}
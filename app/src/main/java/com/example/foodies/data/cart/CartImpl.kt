package com.example.foodies.data.cart

import com.example.foodies.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartImpl : Cart {

    private val _order: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())

    override val order: StateFlow<List<Product>> get() = _order
    private val _sum: MutableStateFlow<Int> = MutableStateFlow(0)
    override val sum: StateFlow<Int> get() = _sum

    override fun increase(product: Product) {
        val count = getCount(product)
        if (count==-1) addToOrder(product)
        else updateCount(product.id, count+1)
        calculateSum()
    }

    override fun decrease(product: Product) {
        val count = getCount(product)
        if (count==1) deleteFromOrder(product.id)
        else updateCount(product.id, count-1)
        calculateSum()
    }

    override fun getCount(product: Product): Int {
        val found = _order.value.find { it.id == product.id }
        return found?.count ?: -1
    }

    private fun addToOrder(product: Product) {
        val newProduct = product.copy(count = 1)
        _order.value += newProduct
    }

    private fun deleteFromOrder(id: Int) {
        _order.value = _order.value.filter { it.id != id }
    }

    private fun updateCount(id: Int, newCount: Int) {
        _order.value = _order.value.map {
            if (it.id == id) { it.copy(count = newCount) }
            else { it }
        }
    }

    private fun calculateSum() {
        var result = 0
        _order.value.forEach { product ->
            result += product.count * product.priceCurrent
        }
        _sum.value = result
    }

}
package com.example.foodies.data.cart

interface ProductRepository {
    fun add(temp: String)
    fun del(temp: String)
}
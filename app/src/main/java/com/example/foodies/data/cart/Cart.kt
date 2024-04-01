package com.example.foodies.data.cart

class Cart : ProductRepository {
    override fun add(temp: String) {
        println("adding $temp")
    }

    override fun del(temp: String) {
        println("deleting $temp")
    }
}
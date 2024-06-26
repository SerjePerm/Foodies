package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import com.example.foodies.data.models.ProductDto
import com.example.foodies.data.models.Tag
import retrofit2.Response

interface NetworkClient {
    suspend fun getCategories(): Response<List<Category>>
    suspend fun getTags(): Response<List<Tag>>
    suspend fun getProducts(): Response<List<ProductDto>>
}
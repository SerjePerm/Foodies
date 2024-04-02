package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import com.example.foodies.data.models.ProductDto
import com.example.foodies.data.models.Tag
import retrofit2.Response
import retrofit2.http.GET

interface FoodiesApi {
    @GET("Categories.json")
    suspend fun getCategories(): Response<List<Category>>

    @GET("Tags.json")
    suspend fun getTags(): Response<List<Tag>>

    @GET("Products.json")
    suspend fun getProducts(): Response<List<ProductDto>>
}
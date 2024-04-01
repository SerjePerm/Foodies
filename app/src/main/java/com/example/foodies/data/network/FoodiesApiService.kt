package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import retrofit2.Response
import retrofit2.http.GET

interface FoodiesApiService {
    @GET("Categories.json")
    suspend fun getCategories(): Response<List<Category>>
}
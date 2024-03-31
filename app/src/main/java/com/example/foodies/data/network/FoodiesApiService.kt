package com.example.foodies.data.network

import retrofit2.http.GET

interface FoodiesApiService {
    @GET("Categories.json")
    suspend fun getCategories(): String
}
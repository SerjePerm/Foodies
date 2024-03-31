package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import retrofit2.Response

interface NetworkClient {
    suspend fun getAllCategories(): String
    //suspend fun getAllCategories(): Response<List<Category>>
}
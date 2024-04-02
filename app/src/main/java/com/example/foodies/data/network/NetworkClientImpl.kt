package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import com.example.foodies.data.models.ProductDto
import com.example.foodies.data.models.Tag
import com.example.foodies.utils.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientImpl : NetworkClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val foodiesApi = retrofit.create(FoodiesApi::class.java)

    override suspend fun getCategories(): Response<List<Category>> {
        return foodiesApi.getCategories()
    }

    override suspend fun getTags(): Response<List<Tag>> {
        return foodiesApi.getTags()
    }

    override suspend fun getProducts(): Response<List<ProductDto>> {
        return foodiesApi.getProducts()
    }

}
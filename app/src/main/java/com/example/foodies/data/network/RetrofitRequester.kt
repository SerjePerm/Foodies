package com.example.foodies.data.network

import com.example.foodies.data.models.Category
import com.example.foodies.utils.Constants.Companion.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequester : NetworkClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        //.addConverterFactory(GsonConverterFactory.create())
        .build()

    private val foodiesApi = retrofit.create(FoodiesApiService::class.java)
    override suspend fun getAllCategories(): String {
        return foodiesApi.getCategories()
    }

}
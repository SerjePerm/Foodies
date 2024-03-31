package com.example.foodies.base

import com.example.foodies.data.network.NetworkClient
import com.example.foodies.data.network.RetrofitRequester
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiAppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): NetworkClient {
        return RetrofitRequester()
    }

}
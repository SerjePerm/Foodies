package com.example.foodies.base

import com.example.foodies.data.cart.Cart
import com.example.foodies.data.cart.CartImpl
import com.example.foodies.data.network.NetworkClient
import com.example.foodies.data.network.NetworkClientImpl
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
        return NetworkClientImpl()
    }

    @Provides
    @Singleton
    fun provideCart(): Cart {
        return CartImpl()
    }

}
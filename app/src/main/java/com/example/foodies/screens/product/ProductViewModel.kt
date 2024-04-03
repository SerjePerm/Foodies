package com.example.foodies.screens.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.cart.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val cart: Cart,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch {
            val tmp = savedStateHandle.get<String>("product")?.toString() ?: "error"
            println("RECEIVED PRODUCT: $tmp")
        }
    }

}
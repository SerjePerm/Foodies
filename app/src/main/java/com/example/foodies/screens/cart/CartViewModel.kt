package com.example.foodies.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.cart.Cart
import com.example.foodies.data.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cart: Cart) : ViewModel() {

    private val _state = MutableStateFlow(CartState.Loading as CartState)
    val state: StateFlow<CartState> = _state.asStateFlow()
    val order: StateFlow<List<Product>> get() = cart.order
    val sum: StateFlow<Int> get() = cart.sum

    init {
        viewModelScope.launch {
            delay(200L)
            _state.value = CartState.Content(data = "data")
        }
    }

    fun onEvent(event: CartEvent) {
        when (event) {
            is CartEvent.Increase -> cart.increase(event.product)
            is CartEvent.Decrease -> cart.decrease(event.product)
        }
    }

}
package com.example.foodies.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.cart.Cart
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

    init {
        viewModelScope.launch {
            delay(200L)
            _state.value = CartState.Content(cartList = emptyList())
            /*
            cart.get().collect{ cartList ->
                _state.value = CartState.Content(cartList = cartList)
            }
             */
        }
        viewModelScope.launch {
            cart._orderFlow.collect{ list ->
                list.forEach {
                    println("flow: $it")
                }
            }
        }

    }

    fun onEvent(event: CartEvent){
        when(event) {
            is CartEvent.Increase -> cart.add(event.product)
            is CartEvent.Decrease -> cart.del(event.product)
        }
    }

}
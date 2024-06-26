package com.example.foodies.screens.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.cart.Cart
import com.example.foodies.data.models.Product
import com.example.foodies.utils.jsonToProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val cart: Cart,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ProductState.Loading as ProductState)
    val state: StateFlow<ProductState> = _state.asStateFlow()
    val order: StateFlow<List<Product>> get() = cart.order

    init {
        viewModelScope.launch {
            val productStr = savedStateHandle.get<String>("product")?.toString() ?: "error"
            val product = jsonToProduct(productStr)
            delay(200L)
            _state.value = ProductState.Content(product = product)
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.CartIncrease -> cart.increase(event.product)
            is ProductEvent.CartDecrease -> cart.decrease(event.product)
        }
    }

}
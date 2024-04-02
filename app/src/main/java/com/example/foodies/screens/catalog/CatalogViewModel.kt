package com.example.foodies.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.cart.Cart
import com.example.foodies.data.models.mapProductDtoToProduct
import com.example.foodies.data.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val networkClient: NetworkClient,
    private val cart: Cart
) : ViewModel() {

    private val _state = MutableStateFlow(CatalogState.Loading as CatalogState)
    val state: StateFlow<CatalogState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            //load data from server
            val categories = networkClient.getCategories().body() ?: emptyList()
            val tags = networkClient.getTags().body() ?: emptyList()
            val productsDto = networkClient.getProducts().body() ?: emptyList()
            val products = productsDto.map { mapProductDtoToProduct(it) }
            delay(200L)
            //set view state
            _state.value = CatalogState.Content(
                categories = categories,
                tags = tags,
                products = products
            )
        }
    }

    fun onEvent(event: CatalogEvent) {
        when (event) {
            is CatalogEvent.AddToCart -> cart.add(event.product)
            is CatalogEvent.DelFromCart -> cart.del(event.product)
        }
    }

}
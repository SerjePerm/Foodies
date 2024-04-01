package com.example.foodies.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(private val retrofit: NetworkClient) : ViewModel() {
    private val _state  = MutableStateFlow(CatalogState.Loading as CatalogState)
    val state: StateFlow<CatalogState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = retrofit.getAllCategories().body() ?: emptyList()
            delay(1000L)
            _state.value = CatalogState.Content(categories = result)
        }
    }

}
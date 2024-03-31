package com.example.foodies.screens.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodies.data.network.RetrofitRequester
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor() : ViewModel() {
//class CatalogViewModel @Inject constructor(private val retrofit: RetrofitRequester) : ViewModel() {

    var temp = "Catalog VM"

    init {
        viewModelScope.launch {
            println("init")
            //temp = retrofit.getAllCategories()
        }
    }

}
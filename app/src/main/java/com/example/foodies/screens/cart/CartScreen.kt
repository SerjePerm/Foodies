package com.example.foodies.screens.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun CartScreen(navHostController: NavHostController, viewModel: CartViewModel) {
    Column {
        Text("CartScreen")
        Text("from viewmodel ${viewModel.temp}")
    }
}
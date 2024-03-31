package com.example.foodies.screens.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProductScreen(navHostController: NavHostController, viewModel: ProductViewModel) {
    Column {
        Text("ProductScreen")
        Text("from viewmodel ${viewModel.temp}")
    }
}
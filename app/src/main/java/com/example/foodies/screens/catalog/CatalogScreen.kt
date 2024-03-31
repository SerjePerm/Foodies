package com.example.foodies.screens.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.foodies.utils.Routes

@Composable
fun CatalogScreen(navHostController: NavHostController, viewModel: CatalogViewModel) {
    Column {
        Text("CatalogScreen")
        Text("from viewmodel ${viewModel.temp}")
        Text("")
        Button(onClick = { navHostController.navigate(Routes.CART) }) {
            Text("Go to Cart")
        }
        Text("")
        Button(onClick = { navHostController.navigate(Routes.PRODUCT) }) {
            Text("Go to product info")
        }
    }
}
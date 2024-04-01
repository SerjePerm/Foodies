package com.example.foodies.screens.catalog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen
import com.example.foodies.utils.Routes

@Composable
fun CatalogScreen(navHostController: NavHostController, viewModel: CatalogViewModel) {
    val state by viewModel.state.collectAsState()
    when (state) {
        CatalogState.Loading -> LoadingScreen()
        is CatalogState.Error -> ErrorScreen(message = (state as CatalogState.Error).message)
        is CatalogState.Content -> ShowContent(
            state = state as CatalogState.Content,
            viewModel = viewModel,
            navHostController = navHostController
        )
    }
}

@Composable
private fun ShowContent(
    state: CatalogState.Content,
    viewModel: CatalogViewModel,
    navHostController: NavHostController
) {
    Column {
        Text("CatalogScreen")
        Text("")
        Button(onClick = { navHostController.navigate(Routes.CART) }) {
            Text("Go to Cart")
        }
        Text("")
        Button(onClick = { navHostController.navigate(Routes.PRODUCT) }) {
            Text("Go to product info")
        }
        LazyColumn {
            items(state.categories) {category ->
                Card {
                    Text(text = "id: ${category.id}")
                    Text(text = "name: ${category.name}")
                }
            }
        }
    }
}
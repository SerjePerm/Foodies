package com.example.foodies.screens.cart

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.foodies.data.models.Product
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen

@Composable
fun CartScreen(navHostController: NavHostController, viewModel: CartViewModel) {
    val state by viewModel.state.collectAsState()
    when (state) {
        CartState.Loading -> LoadingScreen()
        is CartState.Error -> ErrorScreen(message = (state as CartState.Error).message)
        is CartState.Content -> ShowContent(
            state = state as CartState.Content,
            viewModel = viewModel,
            navHostController = navHostController
        )
    }
}

@Composable
private fun ShowContent(
    state: CartState.Content,
    viewModel: CartViewModel,
    navHostController: NavHostController,
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Column {
        Text(
            modifier = Modifier.clickable { onBackPressedDispatcher!!.onBackPressed() },
            text = "CartScreen (go back)"
        )
        //
        val tmp = Product(0, 0, "", "", "", 0, "", 0, "", 0.0, 0.0, 0.0, 0.0, listOf(""), 0)
        Text(
            modifier = Modifier.clickable { viewModel.onEvent(CartEvent.Increase(tmp)) },
            text = "inc"
        )
        Text(
            modifier = Modifier.clickable { viewModel.onEvent(CartEvent.Decrease(tmp)) },
            text = "dec"
        )
        //
        LazyColumn {
            items(state.cartList) {product ->
                UiShowCartCard(product = product, viewModel = viewModel)
            }
        }
    }
}
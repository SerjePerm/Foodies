package com.example.foodies.screens.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.screens.catalog.CatalogState
import com.example.foodies.screens.catalog.CatalogViewModel
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen

@Composable
fun ProductScreen(navHostController: NavHostController, viewModel: ProductViewModel) {
    val state by viewModel.state.collectAsState()
    when (state) {
        ProductState.Loading -> LoadingScreen()
        is ProductState.Error -> ErrorScreen(message = (state as ProductState.Error).message)
        is ProductState.Content -> ShowContent(
            state = state as ProductState.Content,
            viewModel = viewModel,
            navHostController = navHostController
        )
    }
}

@Composable
private fun ShowContent(
    state: ProductState.Content,
    viewModel: ProductViewModel,
    navHostController: NavHostController
) {
    Column {
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = null
        )
        val tmpProduct = state.product
        for (field in tmpProduct.javaClass.declaredFields) {
            field.isAccessible = true
            Row {
                Text("Field: ${field.name}")
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "${field.get(tmpProduct)}",
                    textAlign = TextAlign.Right
                )
            }
        }
    }
}
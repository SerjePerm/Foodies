package com.example.foodies.screens.product

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 6.dp,
                    end = 6.dp,
                    top = paddingValues.calculateTopPadding() + 6.dp,
                    bottom = paddingValues.calculateBottomPadding() + 6.dp
                )
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onBackPressedDispatcher!!.onBackPressed() }
                        .padding(start = 12.dp, top = 12.dp)
                        .size(26.dp)
                        .background(Color.White, CircleShape)
                )
                Image(
                    painter = painterResource(R.drawable.placeholder),
                    contentDescription = null
                )
            }
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

}
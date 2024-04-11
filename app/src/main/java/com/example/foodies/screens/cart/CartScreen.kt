package com.example.foodies.screens.cart

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen
import com.example.foodies.utils.ui_components.ShowBigButton

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
    val data = viewModel.order.collectAsState()
    Scaffold(
        topBar = { ShowTopBar() },
        bottomBar = { ShowBottomBar() }
    )
    { paddingValues ->
        Column(
            modifier = Modifier.padding(
                start = 6.dp,
                end = 6.dp,
                top = paddingValues.calculateTopPadding() + 6.dp,
                bottom = paddingValues.calculateBottomPadding() + 6.dp
            )
        )
        {
            //Empty cart
            if (data.value.isEmpty()) {
                Text(modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp,
                    text = stringResource(R.string.empty_cart)
                )
            }
            //Cart items
            LazyColumn {
                items(data.value) { product ->
                    UiShowCartCard(
                        product = product,
                        viewModel = viewModel,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowTopBar() {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Row(
        modifier = Modifier.padding(all = 10.dp)
    ) {
        Icon(
            modifier = Modifier
                .clickable { onBackPressedDispatcher!!.onBackPressed() }
                .size(24.dp),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = stringResource(R.string.cart),
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
        )
    }
}

@Composable
private fun ShowBottomBar() {
    ShowBigButton(
        title = stringResource(R.string.order_for_summ),
        image = null
    ) {
        println("test")
    }
}
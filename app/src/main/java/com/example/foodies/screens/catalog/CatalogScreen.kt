package com.example.foodies.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.data.models.Product
import com.example.foodies.screens.cart.UiShowCategoryFilter
import com.example.foodies.ui.theme.clrOrange
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen
import com.example.foodies.utils.Routes
import com.example.foodies.utils.ui_components.ShowBigButton

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
    val order = viewModel.order.collectAsState()
    Scaffold(
        topBar = { ShowTopBar() },
        bottomBar = { ShowBottomBar(navHostController) }
    )
    { paddingValues ->
        Column(
            modifier = Modifier.padding(
                start = 6.dp,
                end = 6.dp,
                top = paddingValues.calculateTopPadding() + 6.dp,
                bottom = paddingValues.calculateBottomPadding() + 6.dp
            )
        ) {
            //Categories filters
            LazyRow(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                items(state.categories) { category ->
                    UiShowCategoryFilter(
                        category = category,
                        viewmodel = viewModel,
                        selected = state.selectedCategory
                    )
                }
            }
            //Empty products
            if (state.products.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.empty_products),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                    )
                }
            }
            //Products grid
            LazyVerticalGrid(columns = GridCells.Adaptive(180.dp)) {
                items(state.products) {product ->
                    val count = getCount(product, order)
                    UiShowProductCard(
                        product = product,
                        viewModel = viewModel,
                        navHostController = navHostController,
                        count = count
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(
                top = 24.dp,
                bottom = 16.dp
            )
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null,
            modifier = Modifier
                .size(44.dp)
                .padding(
                    start = 10.dp,
                    top = 10.dp
                )
        )
        Icon(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            tint = clrOrange
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
        )
    }
}

@Composable
private fun ShowBottomBar(navHostController: NavHostController) {
    ShowBigButton(
        title = stringResource(R.string.order_for_summ),
        image = Icons.Default.ShoppingCart
    ) {
        navHostController.navigate(Routes.CART)
    }
}

private fun getCount(product: Product, order: State<List<Product>>): Int {
    val found = order.value.find { it.id == product.id }
    return found?.count ?: -1
}
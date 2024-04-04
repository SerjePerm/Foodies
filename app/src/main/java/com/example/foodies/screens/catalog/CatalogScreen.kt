@file:OptIn(ExperimentalLayoutApi::class)

package com.example.foodies.screens.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodies.screens.cart.UiShowCategoryFilter
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
        Text("")
        Text(
            modifier = Modifier.clickable { navHostController.navigate(Routes.CART) },
            text = "Cart..."
        )
        Text("")
        //Application title

        //Categories filters
        LazyRow {
            items(state.categories) { category ->  
                UiShowCategoryFilter(
                    category = category,
                    viewmodel = viewModel,
                    selected = state.selectedCategory
                )
            }
        }
        Text("")
        //Products grid
        LazyVerticalGrid(columns = GridCells.Adaptive(180.dp)) {
            items(state.products) {product ->
                UiShowProductCard(
                    product = product,
                    viewModel = viewModel,
                    navHostController = navHostController
                )
            }
        }
    }
}

/*
@Composable
private fun ShowExampleResults(viewModel: ParserViewModel, state: ParserState) {
    //results
    FlowRow(modifier = Modifier
        .border(1.dp, Color.Black)
        .padding(all = 6.dp)
    )
    {
        state.exSplitResults.forEach { (index, value) ->
            Text(
                modifier = Modifier.clickable { viewModel.onEvent(ParserEvent.FieldChangeInt(
                    FieldsInt.PARSER_INDEX, index)) },
                text = value,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Clip,
                maxLines = 1,
            )
            Text("   ")
        }
    }
}
 */
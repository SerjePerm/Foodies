package com.example.foodies.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodies.screens.catalog.CatalogScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodies.screens.cart.CartScreen
import com.example.foodies.screens.product.ProductScreen

object Routes {
    const val CATALOG = "catalog_screen"
    const val PRODUCT = "product_screen"
    const val CART = "cart_screen"
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Routes.CATALOG) {
        composable(Routes.CATALOG) {
            CatalogScreen(navHostController = navHostController, viewModel = hiltViewModel() )
        }
        composable(Routes.PRODUCT) {
            ProductScreen(navHostController = navHostController, viewModel = hiltViewModel() )
        }
        composable(Routes.CART) {
            CartScreen(navHostController = navHostController, viewModel = hiltViewModel() )
        }
    }
}
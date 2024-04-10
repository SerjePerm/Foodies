package com.example.foodies.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.data.models.Product
import com.example.foodies.utils.Routes
import com.example.foodies.utils.productToJson

@Composable
fun UiShowCartCard(
    product: Product,
    viewModel: CartViewModel,
    navHostController: NavHostController
) {
    val productStr = productToJson(product)
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .clickable { navHostController.navigate(Routes.PRODUCT+"/"+productStr) }
    ) {
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(text = product.name)
        Text(text = "count: ${product.count}")
        Row {
            Button(onClick = { viewModel.onEvent(CartEvent.Increase(product)) }) {
                Text("+")
            }
            Button(onClick = { viewModel.onEvent(CartEvent.Decrease(product)) }) {
                Text("-")
            }
        }
    }
}
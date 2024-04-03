package com.example.foodies.screens.catalog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.foodies.data.models.Product

@Composable
fun UiShowProductCard(product: Product, viewModel: CatalogViewModel) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Text(text = "id: ${product.id}")
        Text(text = "name: ${product.name}")
        Text(text = "count: ${product.count}")
        Row {
            Button(onClick = { viewModel.onEvent(CatalogEvent.CartIncrease(product)) }) {
                Text("+")
            }
            Button(onClick = { viewModel.onEvent(CatalogEvent.CartDecrease(product)) }) {
                Text("-")
            }
        }
    }
}
package com.example.foodies.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun UiShowProductCard(
    product: Product,
    viewModel: CatalogViewModel,
    navHostController: NavHostController
) {
    val productStr = productToJson(product)
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp, horizontal = 4.dp)
        .clickable { navHostController.navigate(Routes.PRODUCT + "/" + productStr) }
    ) {
        /*
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { product.image },
            imageOptions = ImageOptions(contentScale = ContentScale.Fit),
            loading = { Text(stringResource(R.string.loading))},
            failure = { Icons.Default.Warning }
        )
         */
        // заглушка вместо GlideImage
        Image(
            painter = painterResource(R.drawable.placeholder),
            contentDescription = null
        )
        //Text fields
        Text(text = product.name)
        Text(text = "count: ${product.count}")
        //Buttons
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
package com.example.foodies.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.data.models.Product
import com.example.foodies.ui.theme.clrGray
import com.example.foodies.ui.theme.clrGrayBg
import com.example.foodies.utils.Routes
import com.example.foodies.utils.productToJson
import com.example.foodies.utils.ui_components.CountBtnStyle
import com.example.foodies.utils.ui_components.CountBtnType
import com.example.foodies.utils.ui_components.ShowBigButton
import com.example.foodies.utils.ui_components.ShowCountButton
import com.example.foodies.utils.ui_components.ShowPriceButton

@Composable
fun UiShowProductCard(
    product: Product,
    viewModel: CatalogViewModel,
    navHostController: NavHostController,
    count: Int
) {
    val productStr = productToJson(product)
    Card(
        colors = CardDefaults.cardColors(containerColor = clrGrayBg),
        modifier = Modifier
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
        Box{
            Icon(
                painter = painterResource(id = R.drawable.percent),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 6.dp, top = 6.dp)
                    .size(26.dp)
                    .background(Color.White, CircleShape)
            )
            Image(
                painter = painterResource(R.drawable.placeholder),
                contentDescription = null
            )
        }

        //Text fields
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = product.name,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(start = 6.dp),
            text = "${product.measure} ${product.measureUnit}",
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            color = clrGray,
        )
        //Buttons
        if (count > 0) {
            Row {
                ShowCountButton(type = CountBtnType.MINUS, style = CountBtnStyle.CATALOG) {
                    viewModel.onEvent(CatalogEvent.CartDecrease(product))
                }
                Text(
                    text = count.toString()
                )
                ShowCountButton(type = CountBtnType.PLUS, style = CountBtnStyle.CATALOG) {
                    viewModel.onEvent(CatalogEvent.CartIncrease(product))
                }
            }
        }
        else {
            ShowPriceButton(
                price = product.priceCurrent.toString(),
                priceOld = product.priceOld
            ) {
                viewModel.onEvent(CatalogEvent.CartIncrease(product))
            }
        }
    }
}
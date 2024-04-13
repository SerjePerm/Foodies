package com.example.foodies.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodies.R
import com.example.foodies.data.models.Product
import com.example.foodies.ui.theme.clrGray
import com.example.foodies.utils.CountBtnStyle
import com.example.foodies.utils.CountBtnType
import com.example.foodies.utils.Routes
import com.example.foodies.utils.ShowCountButton
import com.example.foodies.utils.productToJson
import com.example.foodies.utils.realToString

@Composable
fun UiShowCartCard(
    product: Product,
    viewModel: CartViewModel,
    navHostController: NavHostController
) {
    val productStr = productToJson(product)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .clickable { navHostController.navigate(Routes.PRODUCT + "/" + productStr) }
        ) {
            ShowProductImage(product = product)
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .height(96.dp)
                    .padding(start = 6.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = product.name,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp,
                )
                ShowCountBlock(product = product, viewModel = viewModel)
            }
            ShowPriceBlock(product = product)
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = clrGray
        )
    }
}

@Composable
private fun ShowProductImage(product: Product) {
    /*
    GlideImage(
        modifier = Modifier.fillMaxSize(),
        imageModel = { product.image },
        imageOptions = ImageOptions(contentScale = ContentScale.Fit),
        loading = { Text(stringResource(R.string.loading)) },
        failure = { Icons.Default.Warning }
    )
     */
    Image(
        painter = painterResource(R.drawable.placeholder),
        contentDescription = product.name,
        modifier = Modifier.size(96.dp)
    )
}

@Composable
private fun ShowCountBlock(product: Product, viewModel: CartViewModel) {
    Row {
        ShowCountButton(type = CountBtnType.MINUS, style = CountBtnStyle.CART) {
            viewModel.onEvent(CartEvent.Decrease(product))
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = product.count.toString(),
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            )
        }
        ShowCountButton(type = CountBtnType.PLUS, style = CountBtnStyle.CART) {
            viewModel.onEvent(CartEvent.Increase(product))
        }
    }
}

@Composable
private fun ShowPriceBlock(product: Product) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.height(96.dp)
    ) {
        Column {
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = realToString(product.priceCurrent),
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
            )
            if (product.priceOld != null)
                Text(
                    text = realToString(product.priceOld),
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = clrGray,
                    textDecoration = TextDecoration.LineThrough
                )
        }
    }
}
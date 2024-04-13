package com.example.foodies.screens.product

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.R
import com.example.foodies.data.models.Product
import com.example.foodies.ui.theme.clrGray
import com.example.foodies.utils.CountBtnStyle
import com.example.foodies.utils.CountBtnType
import com.example.foodies.utils.ErrorScreen
import com.example.foodies.utils.LoadingScreen
import com.example.foodies.utils.ShowBigButton
import com.example.foodies.utils.ShowCountButton
import com.example.foodies.utils.realToString

@Composable
fun ProductScreen(viewModel: ProductViewModel) {
    val state by viewModel.state.collectAsState()
    when (state) {
        ProductState.Loading -> LoadingScreen()
        is ProductState.Error -> ErrorScreen(message = (state as ProductState.Error).message)
        is ProductState.Content -> ShowContent(
            state = state as ProductState.Content,
            viewModel = viewModel,
        )
    }
}

@Composable
private fun ShowContent(
    state: ProductState.Content,
    viewModel: ProductViewModel
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val order = viewModel.order.collectAsState()
    val product = state.product
    val count = getCount(product, order)
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
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onBackPressedDispatcher!!.onBackPressed() }
                        .padding(start = 12.dp, top = 12.dp)
                        .size(26.dp)
                        .background(Color.White, CircleShape)
                )
                ShowProductImage(product = product)
            }
            //Title
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = product.name,
                fontWeight = FontWeight.W400,
                fontSize = 34.sp,
                lineHeight = 38.sp,
                color = Color.Black
            )
            //Description
            Text(
                text = product.description,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                color = clrGray,
                modifier = Modifier.padding(
                    start = 10.dp,
                    bottom = 16.dp
                )
            )
            //Weight
            ShowRow(
                left = stringResource(R.string.weight),
                right = "${product.measure} ${product.measureUnit}"
            )
            //Energy
            ShowRow(
                left = stringResource(R.string.energy),
                right = "${product.energyPer100grams} " + stringResource(R.string.energy_unit)
            )
            //Proteins
            ShowRow(
                left = stringResource(R.string.proteins),
                right = "${product.proteinsPer100grams} " + stringResource(R.string.gram)
            )
            //Fats
            ShowRow(
                left = stringResource(R.string.fats),
                right = "${product.fatsPer100Grams} " + stringResource(R.string.gram)
            )
            //Carbohydrates
            ShowRow(
                left = stringResource(R.string.carbohydrates),
                right = "${product.carbohydratesPer100Grams} " + stringResource(R.string.gram)
            )
            //Cart button (s)
            ShowPriceBlock(count = count, product = product, viewModel = viewModel)
        }
    }
}

@Composable
private fun ShowRow(left: String, right: String) {
    Row(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = left,
            fontWeight = FontWeight.W400,
            fontSize = 18.sp,
            color = clrGray
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            text = right,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.W400,
            fontSize = 18.sp,
        )
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = clrGray
    )
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
        contentDescription = product.name
    )
}

@Composable
private fun ShowPriceBlock(count: Int, product: Product, viewModel: ProductViewModel) {
    if (count > 0) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 6.dp)
        ) {
            ShowCountButton(type = CountBtnType.MINUS, style = CountBtnStyle.CART) {
                viewModel.onEvent(ProductEvent.CartDecrease(product))
            }
            Box(
                modifier = Modifier.height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                )
            }
            ShowCountButton(type = CountBtnType.PLUS, style = CountBtnStyle.CART) {
                viewModel.onEvent(ProductEvent.CartIncrease(product))
            }
        }
    } else {
        ShowBigButton(
            title = stringResource(R.string.add_to_cart) + realToString(product.priceCurrent),
            image = null
        ) {
            viewModel.onEvent(ProductEvent.CartIncrease(product))
        }
    }
}

private fun getCount(product: Product, order: State<List<Product>>): Int {
    val found = order.value.find { it.id == product.id }
    return found?.count ?: -1
}
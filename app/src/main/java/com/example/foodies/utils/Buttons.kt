package com.example.foodies.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.ui.theme.clrGray
import com.example.foodies.ui.theme.clrGrayBg
import com.example.foodies.ui.theme.clrOrange

@Composable
fun ShowBigButton(title: String, image: ImageVector?, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = clrOrange),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(48.dp)
    ) {
        if (image != null) {
            Icon(
                imageVector = image,
                contentDescription = null
            )
        }
        Text(
            text = title,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun ShowPriceButton(price: String, priceOld: String?, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row {
            Text(
                text = price,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Color.Black
            )
            if (priceOld != null) {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = priceOld,
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.LineThrough,
                    color = clrGray
                )
            }
        }
    }
}

@Composable
fun ShowCountButton(type: CountBtnType, style: CountBtnStyle, onclick: () -> Unit) {
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(horizontal = 0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (style == CountBtnStyle.CATALOG) Color.White else clrGrayBg,
            contentColor = if (style == CountBtnStyle.CATALOG) clrOrange else clrOrange
        ),
        modifier = Modifier
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .height(40.dp)
            .width(40.dp)
    ) {
        Text(
            text = if (type == CountBtnType.PLUS) "+" else "-",
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
        )
    }
}

enum class CountBtnType { PLUS, MINUS }

enum class CountBtnStyle { CATALOG, CART }
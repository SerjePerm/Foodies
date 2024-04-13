package com.example.foodies.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.data.models.Category

@Composable
fun UiShowCategoryFilter(
    category: Category,
    viewmodel: CatalogViewModel,
    selected: Int
) {
    if (category.id == selected) {
        Box(modifier = Modifier
            .clickable { viewmodel.onEvent(CatalogEvent.SelectCategory(category.id)) }
            .background(Color(0xFFF15412), RoundedCornerShape(8.dp))
        ) {
            Text(
                text = category.name,
                fontWeight = W500,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
            )
        }
    } else {
        Text(
            text = category.name,
            fontWeight = W500,
            fontSize = 16.sp,
            modifier = Modifier
                .clickable { viewmodel.onEvent(CatalogEvent.SelectCategory(category.id)) }
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        )
    }
}
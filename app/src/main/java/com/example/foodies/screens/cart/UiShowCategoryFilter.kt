package com.example.foodies.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.data.models.Category
import com.example.foodies.screens.catalog.CatalogEvent
import com.example.foodies.screens.catalog.CatalogViewModel

@Composable
fun UiShowCategoryFilter(
    category: Category,
    viewmodel: CatalogViewModel,
    selected: Int
) {
    if (category.id == selected) {
        Box(modifier = Modifier
            .clickable { viewmodel.onEvent(CatalogEvent.SelectCategory(category.id)) }
            .background(Color(0xFFF15412), RectangleShape)
            .padding(horizontal = 16.dp)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = category.name,
                fontWeight = W500,
                fontSize = 16.sp
            )
        }
    }
    else {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .clickable { viewmodel.onEvent(CatalogEvent.SelectCategory(category.id)) },
            text = category.name,
            fontWeight = W500,
            fontSize = 16.sp
        )
    }
}
package com.lutfi.coffeescape.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lutfi.coffeescape.R
import com.lutfi.jetcoffee.ui.theme.YellowStar

@Composable
fun MyRatingBar(
    maxRating: Int = 10,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    starsColor: Color = YellowStar
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        contentPadding = PaddingValues(2.dp)
    ) {
        items(maxRating, key = { it }) { item ->
            val nomor = item + 1
            Icon(
                painter = if (nomor <= currentRating) painterResource(id = R.drawable.baseline_star_24)
                else painterResource(id = R.drawable.baseline_star_border_24),
                contentDescription = null,
                tint = if (nomor <= currentRating) starsColor else Color.Unspecified,
                modifier = Modifier
                    .clickable { onRatingChanged(nomor) }
                    .padding(4.dp)
                    .size(40.dp)
            )
        }
    }
}
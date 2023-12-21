package com.lutfi.coffeescape.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.data.model.MoodModel
import com.lutfi.coffeescape.ui.home.screen.home.MoodRow

@Composable
fun HeaderHome(
    mood: List<MoodModel>,
    navigateToDetail: (String, Int) -> Unit,
    navigateToSearchResult: (String) -> Unit,
) {
    Box(
        modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.cover_top),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
        Column {
            Search(
                navigateToSearchResult = navigateToSearchResult,
            )
            MoodSection {
                MoodRow(
                    listMood = mood,
                    navigateToDetailMood = { title, icon ->
                        navigateToDetail.invoke(title, icon) }
                )
            }
        }
    }
}

@Composable
fun MoodSection(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_mood),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(124.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        content()
    }
}

@Composable
fun MoodItem(
    modifier: Modifier = Modifier,
    @DrawableRes moodIcon: Int,
    titleMood: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = moodIcon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(60.dp)
        )
        Text(text = titleMood, modifier = modifier, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}
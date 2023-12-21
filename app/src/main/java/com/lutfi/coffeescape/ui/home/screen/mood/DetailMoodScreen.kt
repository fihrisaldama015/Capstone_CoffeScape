package com.lutfi.coffeescape.ui.home.screen.mood

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.CoffeeMood
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.DetailCoffeeResponse
import com.lutfi.coffeescape.ui.component.CoffeeItem

@Composable
fun DetailMoodScreen(
    userName: String,
    context: Context,
    moodType: String,
    icon: Int,
    detailMoodViewModel: DetailMoodViewModel,
    navigateToDetail: (String) -> Unit,
) {
    detailMoodViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailMoodViewModel.getCoffeeBasedOnMood(moodType)
            }

            is UiState.Success -> {
                val data = uiState.data.data
                val message = uiState.data.message
                DetailContent(
                    userName = userName,
                    moodType = moodType,
                    icon = icon,
                    data = data,
                    message = message,
                    navigateToDetail = navigateToDetail,
                )
            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun DetailContent(
    userName: String,
    moodType: String,
    icon: Int,
    data: CoffeeMood?,
    message: String?,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "Hey $userName!\nYou are $moodType today!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
                lineHeight = 24.sp,
            )
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = modifier
                        .height(250.dp)
                        .width(250.dp)
                        .padding(top = 20.dp)
                )
            }
            Text(
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                text = if (message != null) message
                        else "Coffee Coming Soon",
                modifier = Modifier.padding(top = 20.dp)
            )
        }
        if (data != null) {
            CoffeeItem(
                imageUrl = data.imageUrl,
                name = data.name,
                flavorProfiles = data.flavorProfiles,
                rating = data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}
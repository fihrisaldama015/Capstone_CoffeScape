package com.lutfi.coffeescape.ui.home.screen.detail

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.di.Injection
import com.lutfi.coffeescape.ui.ViewModelFactory
import com.lutfi.coffeescape.ui.component.FavoriteButton
import com.lutfi.coffeescape.ui.component.RatingButton
import com.lutfi.coffeescape.ui.component.SectionText2
import com.lutfi.coffeescape.ui.home.HomeViewModel
import com.lutfi.jetcoffee.ui.theme.Brown

@Composable
fun DetailCoffeeScreen(
    userId: String,
    coffeeId: String,
    viewModel: DetailCoffeeViewModel,
    navigateBack: () -> Unit,
    navigateToRating: () -> Unit,
    addFavorite: () -> Unit,
    innerPadding: PaddingValues,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCoffeeById(coffeeId)
            }

            is UiState.Success -> {
                val data = uiState.data
                viewModel.isFavorite.collectAsState(initial = UiState.Loading).value.let { isFavorite ->
                    when (isFavorite) {
                        is UiState.Loading -> {
                            viewModel.getFavoriteById(userId, coffeeId)
                        }
                        is UiState.Success -> {
                           var favorite by rememberSaveable {
                               mutableStateOf(isFavorite.data)
                           }
                            DetailContent(
                                coffee = data,
                                onBackClick = navigateBack,
                                onAddRating = navigateToRating,
                                onFavorite = {
                                     if (favorite) {
                                         favorite = false
                                         viewModel.deleteFromFavorite(userId, data.id)
                                     } else {
                                         favorite = true
                                         viewModel.addToFavorite(userId, data.id)
                                     }
                                },
                                isFavorite = favorite,
                            )
                        }
                        is UiState.Error -> {}
                    }
                }
            }
            is UiState.Error -> {}
        }
    }

}

@Composable
fun DetailContent(
    coffee: DataDetail,
    onBackClick: () -> Unit,
    onAddRating: () -> Unit,
    onFavorite: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(coffee.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.image_placeholder),
            contentDescription = stringResource(R.string.coffee_s_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
        )
        DescriptionBox(name = coffee.name, rating = coffee.rating, isFavorite = isFavorite, onClickFavorite = onFavorite, onRating = onAddRating)
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            if (coffee.recommendedBeans != null) {
                DescriptionRow(
                    title = "Recommended type of beans",
                    result = coffee.recommendedBeans
                )
            }

            if (coffee.roastLevel != null) {
                DescriptionRow(
                    title = "Roast level",
                    result = coffee.roastLevel
                )
            }

            if (coffee.servingStyle != null) {
                DescriptionRow(
                    title = "Serving style",
                    result = coffee.servingStyle
                )
            }
            if (coffee.flavorProfiles != null) {
                DescriptionColumn(
                    title = "Flavor Profile",
                    result = coffee.flavorProfiles
                )
            }

            if (coffee.brewingMethod != null) {
                DescriptionColumn(
                    title = "Brewing Methods",
                    result = coffee.brewingMethod
                )
            }
        }
    }
}

@Composable
fun DescriptionBox(
    name: String,
    rating: Double?,
    isFavorite: Boolean,
    onClickFavorite: () -> Unit,
    onRating: () -> Unit,
) {
    Log.d("apaaaa1","uudusssus")
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Brown)
            .padding(start = 16.dp, end = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Log.d("apaaaa2","uudusssus")
        Text(
            text = name,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(3f)
        )
        Log.d("apaaaa3","uudusssus")
        Row(
            modifier = Modifier
                .weight(2f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Log.d("apaaaa4","uudusssus")
            RatingButton(
                rating = rating,
                onClick = onRating
            )
            Log.d("apaaaa5","uudusssus")
            FavoriteButton(
                isFavorite = isFavorite,
                onClick = onClickFavorite
            )
        }
    }
}

@Composable
fun DescriptionRow(
    title: String,
    result: String,
) {
    Column(
        modifier = Modifier.padding(bottom = 4.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(vertical = 2.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier
                    .weight(2f)
            )
            Text(
                text = result,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .weight(3f)
            )
        }
        Divider(
            modifier = Modifier.padding(top = 2.dp),
            color = Color.LightGray,
        )
    }
}

@Composable
fun DescriptionColumn(
    title: String,
    result: String,
) {
    Column {
        SectionText2(title = title)
        Text(
            text = result,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ),
        )
    }
}
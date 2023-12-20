package com.lutfi.coffeescape.ui.home.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.data.pref.UserPreference
import com.lutfi.coffeescape.data.pref.dataStore
import com.lutfi.coffeescape.ui.component.CoffeeItem
import com.lutfi.jetcoffee.ui.theme.LightGray

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    userId: String,
    viewModel: FavoriteViewModel,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteCoffee(userId)
            }
            is UiState.Success -> {
                if (uiState.data.isNotEmpty()) {
                    CoffeeColumn(
                        coffee = uiState.data,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier,
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "No favorite coffee available.",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            is UiState.Error -> { }
        }
    }
}

@Composable
fun CoffeeColumn(
    coffee: List<DataCoffee>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(coffee, key = { it.id }) { data ->
            CoffeeItem(
                imageUrl = data.imageUrl,
                name = data.name,
                flavorProfiles = data.flavorProfiles,
                rating = data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                color = LightGray,
            )
        }
    }

}
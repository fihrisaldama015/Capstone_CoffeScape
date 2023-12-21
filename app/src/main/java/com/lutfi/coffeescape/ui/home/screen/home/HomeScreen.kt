package com.lutfi.coffeescape.ui.home.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.DataRecommend
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.data.model.MoodModel
import com.lutfi.coffeescape.data.model.dataMood
import com.lutfi.coffeescape.ui.component.BestCoffeeItem
import com.lutfi.coffeescape.ui.component.CoffeeItem
import com.lutfi.coffeescape.ui.component.HeaderHome
import com.lutfi.coffeescape.ui.component.HomeSection
import com.lutfi.coffeescape.ui.component.MoodItem
import com.lutfi.coffeescape.ui.component.Search
import com.lutfi.coffeescape.ui.component.SectionText
import com.lutfi.jetcoffee.ui.theme.LightGray
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    userId: String,
    navigateToDetail: (String) -> Unit,
    navigateToDetailMood: (String, Int) -> Unit,
    navigateToSearchResult: (String) -> Unit,
) {
    var dataCoffee: List<DataCoffee> = emptyList()
    var recommendCoffee: List<DataDetail> = emptyList()

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllCoffee()
            }
            is UiState.Success -> {
                dataCoffee = uiState.data
            }
            is UiState.Error -> {}
        }
    }

    viewModel.recommend.collectAsState(initial = UiState.Loading).value.let { recommend ->
        when (recommend) {
            is UiState.Loading -> {
                viewModel.getRecommendationCoffee(userId)
            }
            is UiState.Success -> {
                recommendCoffee = recommend.data
            }
            is UiState.Error -> {}
        }
    }

    HomeContent(
        coffee = dataCoffee,
        recommend = recommendCoffee,
        mood = dataMood,
        navigateToDetail = navigateToDetail,
        navigateToDetailMood = navigateToDetailMood,
        navigateToSearchResult = navigateToSearchResult,
        modifier = modifier,
    )

}


@Composable
fun HomeContent(
    coffee: List<DataCoffee>,
    recommend: List<DataDetail>,
    mood: List<MoodModel>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    navigateToDetailMood: (String, Int) -> Unit,
    navigateToSearchResult: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            HeaderHome(
                mood = mood,
                navigateToDetail = navigateToDetailMood,
                navigateToSearchResult = navigateToSearchResult,
            )
        }
        item {
            HomeSection(
                title = stringResource(R.string.best_coffee_for_you),
                content = {
                    BestCoffeeRow(
                        coffee = recommend,
                        navigateToDetail = navigateToDetail
                    ) }
            )
            SectionText(title = stringResource(R.string.coffee_drinks_for_you))
        }
        coffee.let {

        }
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

@Composable
fun BestCoffeeRow(
    coffee: List<DataDetail>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier,
    ) {
        items(coffee, key =  { it.id }) { data ->
            BestCoffeeItem(
                imageUrl = data.imageUrl,
                name = data.name,
                rating = data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}

@Composable
fun MoodRow(
    listMood: List<MoodModel>,
    modifier: Modifier = Modifier,
    navigateToDetailMood: (String, Int) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        items(listMood, key = { it.titleMood }) { data ->
            MoodItem(
                moodIcon = data.icon,
                titleMood = data.titleMood,
                modifier = Modifier.clickable {
                    navigateToDetailMood.invoke(data.titleMood.lowercase(Locale.ROOT), data.icon)
                }
            )
        }
    }
}
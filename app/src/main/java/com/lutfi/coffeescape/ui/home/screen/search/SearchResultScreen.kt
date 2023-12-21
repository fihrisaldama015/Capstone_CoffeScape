package com.lutfi.coffeescape.ui.home.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.ui.component.CoffeeItem
import com.lutfi.coffeescape.ui.component.Search2
import com.lutfi.jetcoffee.ui.theme.LightGray
import androidx.compose.runtime.collectAsState as collectAsState1

@Composable
fun SearchResultScreen(
    query: String,
    viewModel: SearchResultViewModel,
    navigateToDetail: (String) -> Unit,
    navigationBack: NavHostController,
    modifier: Modifier = Modifier
) {
    var searchResult: List<DataCoffee> = emptyList()

    viewModel.uiState.collectAsState1(initial = UiState.Loading).value.let { result ->
        when (result) {
            is UiState.Loading -> {
                viewModel.searchCoffee(query)
            }
            is UiState.Success -> {
                searchResult= result.data
            }
            is UiState.Error -> {}
        }
    }

    SearchResultContent(
        query = query,
        result = searchResult,
        navigateToDetail = navigateToDetail,
        navigationBack = navigationBack,
        search = { queri ->
            viewModel.searchCoffee(queri)
        },
        modifier = modifier,
    )
}

@Composable
fun SearchResultContent(
    query: String,
    result: List<DataCoffee>,
    navigateToDetail: (String) -> Unit,
    navigationBack: NavHostController,
    search: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Search2(
            query = query,
            navigationBack = navigationBack,
            searchCoffee = search,
        )
        if (result.isNotEmpty()) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
            ) {
                items(result, key = { it.id }) { data ->
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
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(
                    text = stringResource(R.string.the_coffee_searched_for_was_not_found),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
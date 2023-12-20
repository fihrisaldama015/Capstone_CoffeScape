package com.lutfi.coffeescape.ui.home.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.ui.component.BestCoffeeItem
import com.lutfi.coffeescape.ui.component.CoffeeItem
import com.lutfi.coffeescape.ui.component.HomeSection
import com.lutfi.coffeescape.ui.component.Search
import com.lutfi.coffeescape.ui.component.SectionText
import com.lutfi.jetcoffee.ui.theme.LightGray

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    HomeContent(coffee = dummyCoffee, navigateToDetail = {}, modifier = modifier)
}


@Composable
fun HomeContent(
    coffee: List<DataCoffee>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        item {
            Search()
        }
        item {
            HomeSection(
                title = stringResource(R.string.best_coffee_for_you),
                content = {
                    BestCoffeeRow(
                        coffee = coffee,
                        navigateToDetail = {}
                    ) }
            )
            SectionText(title = stringResource(R.string.coffee_drinks_for_you))
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
    coffee: List<DataCoffee>,
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
                rating = data.rating
            )
        }
    }
}
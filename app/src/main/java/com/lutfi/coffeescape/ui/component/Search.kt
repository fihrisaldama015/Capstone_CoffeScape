package com.lutfi.coffeescape.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lutfi.coffeescape.R
import com.lutfi.jetcoffee.ui.theme.BannerColor
import com.lutfi.jetcoffee.ui.theme.SearchColor

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Search(
    modifier: Modifier = Modifier,
    navigateToSearchResult: (String) -> Unit,
) {
    var queryString by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    SearchBar(
        query = queryString,
        onQueryChange = { newQueryString ->
            queryString = newQueryString
        },
        onSearch = { query ->
            if (query != "") {
                keyboardController?.hide()
                navigateToSearchResult(query)
            }
        },
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Coffee",
                tint = Color.White
            )
        },
        trailingIcon =  {
            if (queryString != "") {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "null",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        queryString = ""
                    }
                )
            }
        },
        placeholder = {
            Text(
                text = stringResource(R.string.placeholder_search),
                color = Color.White
            )
        },
        shape = MaterialTheme.shapes.large,
        colors = SearchBarDefaults.colors(
            containerColor = SearchColor,
            inputFieldColors = TextFieldDefaults.textFieldColors(
                focusedTextColor = Color.White,
                cursorColor = Color.White
            )
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(48.dp)
    ) {

    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Search2(
    query: String,
    modifier: Modifier = Modifier,
    navigationBack: NavController,
    searchCoffee: (String) -> Unit
) {
    var queryString by remember { mutableStateOf(query) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BannerColor)
    ) {
        SearchBar(
            query = queryString,
            onQueryChange = { newQueryString ->
                queryString = newQueryString
            },
            onSearch = { query ->
                keyboardController?.hide()
                searchCoffee(query)
            },
            active = false,
            onActiveChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navigationBack.navigateUp()
                    }
                )
            },
            trailingIcon =  {
                if (queryString != "") {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "null",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            queryString = ""
                        }
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_coffee),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 16.sp)
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = SearchBarDefaults.colors(
                containerColor = SearchColor,
                inputFieldColors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White
                )
            ),
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
        ) {

        }
    }
}
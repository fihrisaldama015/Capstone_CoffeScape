package com.lutfi.coffeescape.ui.home.screen.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.ui.component.BestCoffeeItem
import com.lutfi.coffeescape.ui.component.HomeSection
import com.lutfi.coffeescape.ui.component.ProfileMenuContainer
import com.lutfi.coffeescape.ui.home.screen.home.BestCoffeeRow
import com.lutfi.jetcoffee.ui.theme.Brown

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    image: String? = null,
    name: String,
    email: String,
    logout: () -> Unit,
    navigateToDetail: (String) -> Unit,
    viewModel: ProfileViewModel,
) {
    var showDialog by remember { mutableStateOf(false) }
    var historyCoffee: List<DataDetail> = emptyList()

    viewModel.history.collectAsState(initial = UiState.Loading).value.let { history ->
        when (history) {
            is UiState.Loading -> {
                viewModel.getHistoryCoffee()
            }
            is UiState.Success -> {
                historyCoffee= history.data
            }
            is UiState.Error -> {}
        }
    }

        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
        ) {
            item {
                UserProfileContent(image = image, name = name, email = email,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),)
            }
            item {
                Button(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Brown)
                ) {
                    Text(text = "Logout",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                        ),)
                    if (showDialog) {
                        LogoutConfirmationDialog(
                            onConfirm = {
                                // Call your logout function
                                logout()
                                showDialog = false
                            },
                            onDismiss = {
                                // Handle dismissal
                                showDialog = false
                            }
                        )
                    }
                }
            }
            item { ProfileMenuContainer() }

            if (historyCoffee.isNotEmpty()) {
                item {
                    HomeSection(
                        title = stringResource(R.string.recently_opened_coffee),
                        content = {
                            RecentlyRow(
                                coffee = historyCoffee,
                                navigateToDetail = navigateToDetail,
                            )
                        },
                    )
                }
            }

        }

}

@Composable
fun LogoutConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Logout Confirmation")
        },
        text = {
            Text("Are you sure you want to logout?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm()
                }
            ) {
                Text("Logout")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun UserProfileContent(
    image: String? = null,
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Image(
            painter = painterResource(R.drawable.blank_profile),
            contentDescription = stringResource(R.string.image_profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(12.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f),
        ) {
            Text(
                text = name,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text= email,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                )
            )
        }
    }
}

@Composable
fun RecentlyRow(
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

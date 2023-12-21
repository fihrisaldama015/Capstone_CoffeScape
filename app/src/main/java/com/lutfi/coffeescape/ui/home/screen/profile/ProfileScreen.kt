package com.lutfi.coffeescape.ui.home.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.dummy.dummyCoffee
import com.lutfi.coffeescape.ui.component.BestCoffeeItem
import com.lutfi.coffeescape.ui.component.HomeSection
import com.lutfi.coffeescape.ui.home.screen.home.BestCoffeeRow
import com.lutfi.jetcoffee.ui.theme.Brown

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    image: Int,
    name: String,
    email: String,
    logout: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserProfileContent(image = image, name = name, email = email,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp),)
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
                Text(text = "Logout")
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
            HomeSection(
                title = stringResource(R.string.recently_opened_coffee),
                content = {
                    RecentlyRow(
                        coffee = dummyCoffee,
                        navigateToDetail = {}
                    )
                },
            )
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
    image: Int,
    name: String,
    email: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Image(
            painter = painterResource(image),
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
                rating = data.rating,
                modifier = Modifier.clickable {
                    navigateToDetail(data.id)
                }
            )
        }
    }
}

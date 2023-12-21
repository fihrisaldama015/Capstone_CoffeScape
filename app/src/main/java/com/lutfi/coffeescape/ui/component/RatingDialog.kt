package com.lutfi.coffeescape.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lutfi.coffeescape.R
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.ui.home.screen.detail.DetailCoffeeViewModel
import com.lutfi.jetcoffee.ui.theme.BannerColor
import com.lutfi.jetcoffee.ui.theme.Brown
import androidx.compose.runtime.collectAsState as collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingDialog(
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setSnackBarState: (Boolean) -> Unit,
    onAddRating: (String, String) -> Unit,
) {
    var myRating by remember { mutableStateOf(0) }
    var txtFieldError by remember { mutableStateOf("") }
    var txtField by remember { mutableStateOf(value) }
    Dialog(
        onDismissRequest = {
            setShowDialog(false)
        }
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.White,
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Text(
                            text = "How would you rate this coffee?",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                            ),
                            modifier = Modifier.weight(7f)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_cancel_24),
                            contentDescription = null,
                            tint = Color.DarkGray,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .weight(1f)
                                .clickable { setShowDialog(false) }
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    MyRatingBar(
                        currentRating = myRating,
                        onRatingChanged = {myRating = it }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 1.dp,
                                    color = if (txtFieldError.isEmpty()) BannerColor
                                    else Color.Red
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        value = txtField,
                        onValueChange = {
                            txtField = it
                        },
                        placeholder = {
                            Text(text = "Write comment about this coffee")
                        },
                        maxLines = 5,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                    ) {
                        Button(
                            onClick = {
                                onAddRating(myRating.toString(), txtField)
                                if (txtField.isEmpty()) {
                                    txtFieldError = "Field can not be empty"
                                    return@Button
                                }
                                setShowDialog(false)
                                setSnackBarState(true)
                            },
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Brown),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(
                                text = "Send"
                            )
                        }
                    }
                }
            }
        }
    }
}
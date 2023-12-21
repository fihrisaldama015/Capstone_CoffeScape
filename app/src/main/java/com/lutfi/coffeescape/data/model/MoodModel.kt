package com.lutfi.coffeescape.data.model

import androidx.annotation.DrawableRes
import com.lutfi.coffeescape.R

data class MoodModel(
    @DrawableRes val icon: Int,
    val titleMood: String
)

val dataMood = listOf(
    MoodModel(
        icon = R.drawable.ic_happy,
        titleMood = "Happy"
    ),
    MoodModel(
        icon = R.drawable.ic_sad,
        titleMood = "Sad"
    ),
    MoodModel(
        icon = R.drawable.ic_lonely,
        titleMood = "Lonely"
    ),
    MoodModel(
        icon = R.drawable.ic_bored,
        titleMood = "Bored"
    )
)
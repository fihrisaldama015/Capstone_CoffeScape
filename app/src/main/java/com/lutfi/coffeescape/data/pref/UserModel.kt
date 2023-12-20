package com.lutfi.coffeescape.data.pref

data class UserModel(
    val email: String,
    val id: String,
    val token: String,
    val isLogin: Boolean = false
)
package com.lutfi.coffeescape.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailCoffee : Screen("home/{coffeeId}") {
        fun createRoute(coffeeId: Long) = "home/$coffeeId"
    }
    object MoodCoffee : Screen("home/{mood}") {
        fun createRoute(mood: String) = "home/$mood"
    }
}
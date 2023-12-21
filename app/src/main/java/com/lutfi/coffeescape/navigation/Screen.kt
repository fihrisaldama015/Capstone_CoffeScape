package com.lutfi.coffeescape.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailCoffee : Screen("home/{coffeeId}") {
        fun createRoute(coffeeId: String) = "home/$coffeeId"
    }

    object MoodCoffee : Screen("home/{mood}/{icon}") {
        fun createRoute(mood: String, icon: Int) = "home/$mood/$icon"
    }

    object SearchResult : Screen("searchResult/{query}") {
        fun withArg(query: Pair<String, String>): String {
            return "searchResult/${query.second}"
        }
    }
}
package com.lutfi.coffeescape.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.di.Injection
import com.lutfi.coffeescape.ui.home.HomeViewModel
import com.lutfi.coffeescape.ui.home.screen.detail.DetailCoffeeViewModel
import com.lutfi.coffeescape.ui.home.screen.favorite.FavoriteViewModel
import com.lutfi.coffeescape.ui.home.screen.home.HomeScreenViewModel
import com.lutfi.coffeescape.ui.home.screen.mood.DetailMoodViewModel
import com.lutfi.coffeescape.ui.home.screen.profile.ProfileViewModel
import com.lutfi.coffeescape.ui.home.screen.search.SearchResultViewModel
import com.lutfi.coffeescape.ui.login.LoginViewModel
import com.lutfi.coffeescape.ui.register.RegisterViewModel

class ViewModelFactory private constructor(private val repository: CoffeeScapeRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailCoffeeViewModel::class.java) -> {
                DetailCoffeeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) -> {
                HomeScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailMoodViewModel::class.java) -> {
                DetailMoodViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchResultViewModel::class.java) -> {
                SearchResultViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { INSTANCE = it }
    }

}
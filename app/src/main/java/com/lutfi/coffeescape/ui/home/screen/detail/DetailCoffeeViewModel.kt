package com.lutfi.coffeescape.ui.home.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCoffeeViewModel(
    private val repository: CoffeeScapeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<DataDetail>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<DataDetail>>
        get() = _uiState

    private val _isFavorite: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Loading)
    val isFavorite: StateFlow<UiState<Boolean>>
        get() = _isFavorite

    fun getCoffeeById(coffeeId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCoffeeById(coffeeId))
        }
    }

    fun getFavoriteById(userId: String, coffeeId:String) {
        viewModelScope.launch {
            _isFavorite.value = UiState.Loading
            _isFavorite.value = UiState.Success(repository.getFavoriteById(userId, coffeeId))
        }
    }

    fun addToFavorite(userId: String, coffeeId: String) {
        viewModelScope.launch {
            repository.addFavoriteCoffee(userId, coffeeId)
        }
    }

    fun deleteFromFavorite(userId: String, coffeeId: String) {
        viewModelScope.launch {
            repository.deleteFavoriteCoffee(userId, coffeeId)
        }
    }

}
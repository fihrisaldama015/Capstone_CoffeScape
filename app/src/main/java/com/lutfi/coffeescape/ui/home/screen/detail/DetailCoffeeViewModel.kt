package com.lutfi.coffeescape.ui.home.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.gson.Gson
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

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

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?>
        get() = _message

    fun getCoffeeById(coffeeId: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                _uiState.value = UiState.Success(repository.getCoffeeById(coffeeId))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _message.value = errorMessage
            }
        }
    }

    fun getFavoriteById(userId: String, coffeeId:String) {
        viewModelScope.launch {
            _isFavorite.value = UiState.Loading
            _isFavorite.value = UiState.Success(repository.getFavoriteById(userId, coffeeId))
        }
        repository.insertHistory(coffeeId)
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

    fun addCoffeeRating(userId: String, coffeeId: String, rating: String, comment: String) {
        viewModelScope.launch {
            try {
                _message.value = repository.addCoffeeRating(userId, coffeeId, rating, comment)
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _message.value = errorMessage
            }
        }
    }

}
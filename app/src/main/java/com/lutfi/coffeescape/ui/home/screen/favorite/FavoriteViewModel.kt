package com.lutfi.coffeescape.ui.home.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FavoriteViewModel(
    private val repository: CoffeeScapeRepository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _messages = MutableLiveData<String?>()
    val messages: LiveData<String?> = _messages

    private val _listFavorite = MutableLiveData<List<DataCoffee?>>()
    val listFavorite: LiveData<List<DataCoffee?>> = _listFavorite

    private val _uiState: MutableStateFlow<UiState<List<DataCoffee>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataCoffee>>>
        get() = _uiState

    fun getFavoriteCoffee(userId: String) {
        viewModelScope.launch {
            repository.getFavoriteCoffee(userId)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { coffee ->
                    _uiState.value = UiState.Success(coffee)
                }
        }
    }

    fun getFavoriteCoffee2(userId: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
//                _listFavorite.value = repository.getFavoriteCoffee(userId).data
                _isLoading.value = false

            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _isLoading.value = false
                _messages.value = errorMessage
            }
        }
    }
}
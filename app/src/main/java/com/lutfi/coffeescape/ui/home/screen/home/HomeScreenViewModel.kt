package com.lutfi.coffeescape.ui.home.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomeScreenViewModel(
    private val repository: CoffeeScapeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<DataCoffee>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DataCoffee>>>
        get() = _uiState

    private val _recommend: MutableStateFlow<UiState<List<DataDetail>>> = MutableStateFlow(UiState.Loading)
    val recommend: StateFlow<UiState<List<DataDetail>>>
        get() = _recommend

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?>
        get() = _message

    fun getAllCoffee() {
        viewModelScope.launch {
            try {
                repository.getAllCoffee()
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect { coffee ->
                        _uiState.value = UiState.Success(coffee)
                    }
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _message.value = errorMessage
            }
        }
    }

    fun getRecommendationCoffee(userId: String) {
        viewModelScope.launch {
            try {
                repository.getRecommendationCoffee(userId)
                    .catch {
                        _recommend.value = UiState.Error(it.message.toString())
                    }
                    .collect { coffee ->
                        _recommend.value = UiState.Success(coffee)
                    }
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _message.value = errorMessage
            }
        }
    }
}
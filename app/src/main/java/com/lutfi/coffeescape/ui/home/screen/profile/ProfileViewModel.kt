package com.lutfi.coffeescape.ui.home.screen.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ProfileViewModel(
    private val repository: CoffeeScapeRepository
) : ViewModel() {

    private val _history: MutableStateFlow<UiState<List<DataDetail>>> = MutableStateFlow(UiState.Loading)
    val history: StateFlow<UiState<List<DataDetail>>>
        get() = _history

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?>
        get() = _message

    fun getHistoryCoffee() {
        viewModelScope.launch {
            try {
                repository.getHistoryCoffee()
                    .catch {
                        _history.value = UiState.Error(it.message.toString())
                    }
                    .collect { coffee ->
                        _history.value = UiState.Success(coffee)
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
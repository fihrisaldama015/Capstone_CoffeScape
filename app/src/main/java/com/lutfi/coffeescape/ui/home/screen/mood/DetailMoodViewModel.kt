package com.lutfi.coffeescape.ui.home.screen.mood

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.common.UiState
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.DetailCoffeeResponse
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import com.lutfi.coffeescape.data.api.response.MoodDetailResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailMoodViewModel(
    private val repository: CoffeeScapeRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<MoodDetailResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MoodDetailResponse>>
        get() = _uiState

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?>
        get() = _message

    fun getCoffeeBasedOnMood(moodType: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                _uiState.value = UiState.Success(repository.getCoffeeBasedOnMood(moodType))
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _message.value = errorMessage
            }
        }
    }
}
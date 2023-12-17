package com.lutfi.coffeescape.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(private val repository: CoffeeScapeRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _messages = MutableLiveData<String?>()
    val messages: LiveData<String?> = _messages

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess
    fun registerUser(name:String, email:String, password:String) {
        viewModelScope.launch {
            try {
                _isLoading.value = false
                _messages.value = repository.registerUser(name, email, password)
                _isSuccess.value = true
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _isLoading.value = false
                _messages.value = errorMessage
                _isSuccess.value = false
            }
        }
    }
}
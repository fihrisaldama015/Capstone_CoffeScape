package com.lutfi.coffeescape.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataLogin
import com.lutfi.coffeescape.data.api.response.ErrorResponse
import com.lutfi.coffeescape.data.pref.UserModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: CoffeeScapeRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _messages = MutableLiveData<String?>()
    val messages: LiveData<String?> = _messages

    private val _token = MutableLiveData<String?>()
    val token: LiveData<String?> = _token

    private val _dataLogin = MutableLiveData<DataLogin>()
    val dataLogin: LiveData<DataLogin> = _dataLogin

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.loginUser(email, password)
                _isLoading.value = false
                _isSuccess.value = true
                _dataLogin.value = response.data
                _token.value = response.data.token
                _messages.value = response.message
            } catch (e: HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
                val errorMessage = errorBody.message
                _isLoading.value = false
                _isSuccess.value = false
                _messages.value = errorMessage
            }
        }
    }
}
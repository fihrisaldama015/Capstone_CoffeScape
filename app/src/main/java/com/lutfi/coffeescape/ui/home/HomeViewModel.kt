package com.lutfi.coffeescape.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.response.DataUser
import com.lutfi.coffeescape.data.pref.UserModel
import kotlinx.coroutines.launch
import okhttp3.internal.concurrent.Task

class HomeViewModel(private val repository: CoffeeScapeRepository) : ViewModel() {

    private val _userProfile = MutableLiveData<DataUser>()
    val userProfile: LiveData<DataUser> = _userProfile

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }
    }

    fun getUserProfile(userId: String) {
        viewModelScope.launch {
            _userProfile.value = repository.getUserProfile(userId)
        }
    }

}
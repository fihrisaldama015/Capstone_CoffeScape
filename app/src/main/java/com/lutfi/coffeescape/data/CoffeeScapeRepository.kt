package com.lutfi.coffeescape.data

import com.lutfi.coffeescape.data.api.response.LoginResponse
import com.lutfi.coffeescape.data.api.retrofit.ApiService
import com.lutfi.coffeescape.data.pref.UserModel
import com.lutfi.coffeescape.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class CoffeeScapeRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    suspend fun registerUser(name: String, email: String, password: String) : String{
        return apiService.register(name, email, password).message.toString()
    }

    suspend fun loginUser(email: String, password: String) : LoginResponse {
        return apiService.login(email, password)
    }

    companion object {
        @Volatile
        private var instance: CoffeeScapeRepository? = null

        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): CoffeeScapeRepository =
            instance ?: synchronized(this) {
                instance ?: CoffeeScapeRepository(userPreference, apiService)
            }.also { instance = it }
    }
}
package com.lutfi.coffeescape.data

import com.lutfi.coffeescape.data.api.response.CoffeeResponse
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.DataUser
import com.lutfi.coffeescape.data.api.response.DeleteFavoriteRequest
import com.lutfi.coffeescape.data.api.response.LoginResponse
import com.lutfi.coffeescape.data.api.response.UserProfileResponse
import com.lutfi.coffeescape.data.api.retrofit.ApiService
import com.lutfi.coffeescape.data.pref.UserModel
import com.lutfi.coffeescape.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

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

    suspend fun getUserProfile(id: String) : DataUser {
        return apiService.getUserProfile(id).data
    }

    suspend fun getFavoriteCoffee(id: String): Flow<List<DataCoffee>> {
        return flowOf(apiService.getFavoriteCoffee(id).data)
    }

    suspend fun addFavoriteCoffee(id: String, coffeeId: String): DataUser {
        return apiService.addFavoriteCoffee(id, coffeeId).data
    }

    suspend fun deleteFavoriteCoffee(id: String, coffeeId: String): DataUser {
        val deleteId = DeleteFavoriteRequest(coffeeId)
        return apiService.deleteFavoriteCoffee(id, deleteId).data
    }

    suspend fun getFavoriteById(userId: String, coffeeId: String): Boolean {
        val favoriteCoffee = apiService.getFavoriteCoffee(userId).data
        return favoriteCoffee.any {
            it.id == coffeeId
        }
    }
    suspend fun getCoffeeById(coffeeId: String): DataDetail {
        return apiService.getCoffeeById(coffeeId).data
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
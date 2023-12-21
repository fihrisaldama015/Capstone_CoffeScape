package com.lutfi.coffeescape.data

import androidx.lifecycle.asFlow
import com.lutfi.coffeescape.data.api.response.DataCoffee
import com.lutfi.coffeescape.data.api.response.DataDetail
import com.lutfi.coffeescape.data.api.response.DataUser
import com.lutfi.coffeescape.data.api.response.DeleteFavoriteRequest
import com.lutfi.coffeescape.data.api.response.LoginResponse
import com.lutfi.coffeescape.data.api.response.MoodDetailResponse
import com.lutfi.coffeescape.data.api.retrofit.ApiService
import com.lutfi.coffeescape.data.database.CoffeeDatabase
import com.lutfi.coffeescape.data.pref.UserModel
import com.lutfi.coffeescape.data.pref.UserPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CoffeeScapeRepository private constructor(
    private val userPreference: UserPreference,
    private val coffeeDatabase: CoffeeDatabase,
    private val apiService: ApiService,
) {
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        executorService.execute {
            coffeeDatabase.coffeeDao().deleteHistory()
        }
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

    suspend fun addCoffeeRating(userId: String, coffeeId: String, rating: String, comment: String): String {
        return apiService.AddCoffeeRating(userId, coffeeId, rating, comment).message.toString()
    }

    suspend fun getAllCoffee(): Flow<List<DataCoffee>> {
        return flowOf(apiService.getAllCoffee().data)
    }

    suspend fun getRecommendationCoffee(userId: String): Flow<List<DataDetail>> {
        val recommendId = apiService.getRecommendationCoffee(userId).data.prediction
        val recommendation: MutableList<DataDetail> = mutableListOf()
        for (id in recommendId) {
            val coffee = apiService.getCoffeeById(id.toString()).data
            recommendation.add(coffee)
        }
        return flowOf(recommendation)
    }
    fun insertHistory(coffeeId: String) {
        executorService.execute {
            coffeeDatabase.coffeeDao().insertHistory(coffeeId)
        }
    }

    suspend fun getHistoryCoffee(): Flow<List<DataDetail>> = coffeeDatabase.coffeeDao().getCoffeeIdHistory().asFlow()
        .flatMapLatest { historyId ->
            flow {
                val historyCoffee: MutableList<DataDetail> = mutableListOf()

                historyId?.forEach { id ->
                    val coffee = apiService.getCoffeeById(id).data
                    historyCoffee.add(coffee)
                }

                emit(historyCoffee)
            }
        }
        .flowOn(Dispatchers.IO)

    suspend fun getCoffeeBasedOnMood(moodType: String): MoodDetailResponse {
        return apiService.getCoffeeBasedOnMood(moodType)
    }

    suspend fun searchCoffee(query: String): Flow<List<DataCoffee>> {
        val resultByName = apiService.searchCoffeeByName(query).data
        val resultByFlavor = apiService.searchCoffeeByFlavor(query).data
        return flowOf((resultByName + resultByFlavor).distinctBy { it.id })
    }

    companion object {
        @Volatile
        private var instance: CoffeeScapeRepository? = null

        fun getInstance(
            userPreference: UserPreference,
            database: CoffeeDatabase,
            apiService: ApiService
        ): CoffeeScapeRepository =
            instance ?: synchronized(this) {
                instance ?: CoffeeScapeRepository(userPreference, database, apiService)
            }.also { instance = it }
    }
}
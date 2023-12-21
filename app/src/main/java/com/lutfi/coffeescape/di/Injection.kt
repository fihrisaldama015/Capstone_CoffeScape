package com.lutfi.coffeescape.di

import android.content.Context
import com.lutfi.coffeescape.data.CoffeeScapeRepository
import com.lutfi.coffeescape.data.api.retrofit.ApiConfig
import com.lutfi.coffeescape.data.database.CoffeeDatabase
import com.lutfi.coffeescape.data.pref.UserPreference
import com.lutfi.coffeescape.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): CoffeeScapeRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val database = CoffeeDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService(context)
        return CoffeeScapeRepository.getInstance(pref, database, apiService)
    }
}
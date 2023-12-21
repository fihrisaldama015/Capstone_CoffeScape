package com.lutfi.coffeescape.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoffeeDao {
    @Query("INSERT INTO coffee_history (coffee_id, access_time) VALUES (:coffeeId, CURRENT_TIMESTAMP)")
    fun insertHistory(coffeeId: String)

    @Query("SELECT DISTINCT coffee_id FROM coffee_history ORDER BY access_time DESC Limit 10")
    fun getCoffeeIdHistory(): LiveData<List<String>>

    @Query("DELETE FROM coffee_history")
    fun deleteHistory()
}
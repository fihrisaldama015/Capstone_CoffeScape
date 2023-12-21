package com.lutfi.coffeescape.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoffeeHistory::class], version = 1)
abstract class CoffeeDatabase : RoomDatabase() {
    abstract fun coffeeDao(): CoffeeDao

    companion object {
        @Volatile
        private var INSTANCE: CoffeeDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): CoffeeDatabase {
            if (INSTANCE == null) {
                synchronized(CoffeeDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        CoffeeDatabase::class.java, "coffee_database")
                        .build()
                }
            }
            return INSTANCE as CoffeeDatabase
        }
    }
}
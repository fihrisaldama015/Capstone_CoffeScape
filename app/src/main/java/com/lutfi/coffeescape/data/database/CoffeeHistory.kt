package com.lutfi.coffeescape.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "coffee_history")
@Parcelize
data class CoffeeHistory(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "coffee_id")
    val coffeeId: String,

    @ColumnInfo(name = "access_time", defaultValue = "CURRENT_TIMESTAMP")
    val accessTime: String

) : Parcelable
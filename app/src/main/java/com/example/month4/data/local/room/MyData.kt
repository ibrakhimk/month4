package com.example.month4.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_data_table")
data class MyData(
    @PrimaryKey val id: Int,
    val url: String
    // Другие поля здесь...
)
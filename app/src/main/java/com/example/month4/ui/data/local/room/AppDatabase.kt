package com.example.month4.ui.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.month4.ui.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
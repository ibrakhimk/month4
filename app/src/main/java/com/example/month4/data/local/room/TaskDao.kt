package com.example.month4.data.local.room

import androidx.room.*
import com.example.month4.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: List<Task>)

    @Update
    fun update(task: Task)
}
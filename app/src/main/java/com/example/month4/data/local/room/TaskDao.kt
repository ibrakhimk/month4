package com.example.month4.data.local.room

import androidx.room.*
import com.example.month4.model.Task
@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>
    //Save task
    @Insert
    fun insert(task: Task)
    //Delete task
    @Delete
    fun delete(task: List<Task>)

    @Update
    fun update(task: Task)
}
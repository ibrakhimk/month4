package com.example.month4.ui.data.local.room

import androidx.room.*
import com.example.month4.ui.model.Task
@Dao
interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAll(): List<Task>
    //Save task
    @Insert
    fun insert(task: Task)
    //Delete task
    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
  //  @Query("DELETE FROM my_data_table WHERE id = :id")
    //fun deleteById(id: Int)

}
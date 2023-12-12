package com.example.myfirstapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    // Insert a task into the database
    @Insert
    suspend fun insertTask(task: DataTask)

    // Get all tasks from the database
    @Query("SELECT * FROM task_table")
    fun getTasks(): LiveData<List<DataTask>>

    // Delete a task from the database
    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)

    // Update isSelected field of a task
    @Query("UPDATE task_table SET isSelected = :isSelected WHERE id = :taskId")
    suspend fun updateTask(taskId: Int, isSelected: Boolean)

    // clean the database
    @Query("DELETE FROM task_table")
    suspend fun cleanDatabase()

}
package com.example.myfirstapplication.data

import androidx.lifecycle.LiveData

class TaskRepository(private val dao: TaskDao) {

    val getTasks : LiveData<List<DataTask>> = dao.getTasks()

    suspend fun addTask(task: DataTask) {
        dao.insertTask(task)
    }

    suspend fun deleteTask(taskId: Int) {
        dao.deleteTask(taskId)
    }

    suspend fun updateTask(taskId: Int, isSelected: Boolean) {
        dao.updateTask(taskId, isSelected)
    }

    suspend fun cleanDatabase() {
        dao.cleanDatabase()
    }

}
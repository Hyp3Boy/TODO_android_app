package com.example.myfirstapplication.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapplication.data.DataTask
import com.example.myfirstapplication.data.TaskDao
import com.example.myfirstapplication.data.TaskDatabase
import com.example.myfirstapplication.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel(application:Application) : AndroidViewModel(application ) {
    val tasks: LiveData<List<DataTask>>
    private val repository: TaskRepository

    init{
        val dao = TaskDatabase.getDatabase(application).dao
        repository = TaskRepository(dao)
        tasks = repository.getTasks
    }

    fun addTask(task: DataTask){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun deleteTask(taskId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(taskId)
        }
    }

    fun updateTask(taskId: Int, isSelected: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(taskId, isSelected)
        }
    }

    fun cleanDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.cleanDatabase()
        }
    }
}
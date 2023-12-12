package com.example.myfirstapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.myfirstapplication.TaskCategory

@Entity(tableName = "task_table")
data class DataTask(
    val name: String,
    @TypeConverters(TaskCategoryConverter::class)
    val category: TaskCategory,
    var isSelected: Boolean = false,

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)
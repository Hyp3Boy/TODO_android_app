package com.example.myfirstapplication.data

import androidx.room.TypeConverter
import com.example.myfirstapplication.TaskCategory

class TaskCategoryConverter {
    @TypeConverter
    fun fromTaskCategory(taskCategory: TaskCategory): String {
        when (taskCategory) {
            TaskCategory.UNIVERSITY -> return "UNIVERSITY"
            TaskCategory.PERSONAL -> return "PERSONAL"
            else -> return "OTHER"
        }
    }

    @TypeConverter
    fun toTaskCategory(taskCategory: String): TaskCategory {
        when (taskCategory) {
            "UNIVERSITY" -> return TaskCategory.UNIVERSITY
            "PERSONAL" -> return TaskCategory.PERSONAL
            else -> return TaskCategory.OTHER
        }
    }
}
package com.example.myfirstapplication

import androidx.room.TypeConverter

class TaskCategoryConverter {

    @TypeConverter
    fun fromTaskCategory(taskCategory: TaskCategory): String {
        return when (taskCategory) {
            is TaskCategory.UNIVERSITY -> "UNIVERSITY"
            is TaskCategory.PERSONAL -> "PERSONAL"
            is TaskCategory.OTHER -> "OTHER"
        }
    }

    @TypeConverter
    fun toTaskCategory(taskCategory: String): TaskCategory {
        return when (taskCategory) {
            "UNIVERSITY" -> TaskCategory.UNIVERSITY
            "PERSONAL" -> TaskCategory.PERSONAL
            "OTHER" -> TaskCategory.OTHER
            else -> TaskCategory.OTHER
        }
    }

}
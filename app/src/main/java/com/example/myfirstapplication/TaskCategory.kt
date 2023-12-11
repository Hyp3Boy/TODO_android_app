package com.example.myfirstapplication

sealed class TaskCategory(
    var isSelected : Boolean = false
) {
    object UNIVERSITY : TaskCategory()
    object PERSONAL : TaskCategory()
    object OTHER : TaskCategory()
}
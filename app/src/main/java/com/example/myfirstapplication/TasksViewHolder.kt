package com.example.myfirstapplication

import android.content.res.ColorStateList
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.data.DataTask

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)
    fun render(task: DataTask) {
        Log.i("TasksViewHolder", "taskrender")

        tvTask.text = task.name
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
        cbTask.isChecked = task.isSelected

        val colorRes = when (task.category) {
            TaskCategory.UNIVERSITY -> R.color.todo_uni_category


            TaskCategory.PERSONAL -> R.color.todo_personal_category

            TaskCategory.OTHER -> R.color.todo_other_category

        }

        cbTask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                itemView.context,
                colorRes
            )
        )

    }
}
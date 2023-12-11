package com.example.myfirstapplication

import android.provider.Settings.Global.getString
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName:TextView = view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    private val cvCategory: CardView = view.findViewById(R.id.cvCategory)

    fun render(taskCategory: TaskCategory) {

        var color = if(taskCategory.isSelected) {
            R.color.todo_background_disabled
        }
        else {
            R.color.todo_background_card
        }

        cvCategory.setCardBackgroundColor(
            ContextCompat.getColor(
                itemView.context,
                color
            )
        )

        when(taskCategory){
            TaskCategory.UNIVERSITY -> {
                tvCategoryName.text = "University"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.todo_uni_category
                    )
                )
            }
            TaskCategory.PERSONAL -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.todo_personal_category
                    )
                )
            }
            TaskCategory.OTHER -> {
                tvCategoryName.text = "Other"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.todo_other_category
                    )
                )
            }
        }

    }

}
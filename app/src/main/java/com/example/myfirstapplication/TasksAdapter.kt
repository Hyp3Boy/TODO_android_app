package com.example.myfirstapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.data.DataTask

class TasksAdapter(private val onTaskSelected: (Int) -> Unit, categories: List<TaskCategory>) :

    RecyclerView.Adapter<TasksViewHolder>() {
        private var tasks = emptyList<DataTask>()
        private var categories = categories
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        Log.i("TasksAdapter", "llegue hasta aca")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        holder.itemView.setOnClickListener {
            onTaskSelected(position)
        }
    }

    fun setData(task: List<DataTask>, categories: List<TaskCategory>){
        this.categories = categories
        val selectedCategories = categories.filter { !it.isSelected }
        val newTasks = task.filter { selectedCategories.contains(it.category) }
        this.tasks = newTasks
        notifyDataSetChanged()
    }
}
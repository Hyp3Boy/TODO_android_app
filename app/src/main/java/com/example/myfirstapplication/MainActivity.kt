package com.example.myfirstapplication

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.*
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.data.DataTask
import com.example.myfirstapplication.presentation.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity() {
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvTasks: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var fabAddTask: FloatingActionButton

    val categories = listOf(
        TaskCategory.UNIVERSITY, TaskCategory.PERSONAL, TaskCategory.OTHER
    )

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var tasks: List<DataTask>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isEmpty()) {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val selectedCategoryId = rgCategories.checkedRadioButtonId
            val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedCategoryId)
            val currentCategory = when (selectedRadioButton.text) {
                getString(R.string.uniTask) -> TaskCategory.UNIVERSITY
                getString(R.string.personalTask) -> TaskCategory.PERSONAL
                else -> TaskCategory.OTHER
            }

            taskViewModel.addTask(DataTask(currentTask, currentCategory))
            dialog.hide()
        }
        dialog.show()

    }


    private fun initComponent() {

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        rvCategories = findViewById<RecyclerView>(R.id.rvCategories)
        rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories, ::onCategorySelected)
        tasksAdapter = TasksAdapter(::onTaskSelected, categories)
        taskViewModel.tasks.observe(this, Observer { task ->
            tasksAdapter.setData(task, categories)
            this.tasks = task
            val handler = android.os.Handler()
            handler.postDelayed({
                val selectedTasks = task.filter { it.isSelected }
                // erase selected tasks
                selectedTasks.forEach {
                    deleteTaskFromDatabase(it.id)
                }
            },100)
        })
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter

        Log.i("MainActivity", "UI initialized")

    }

    private fun onTaskSelected(position: Int) {
        val selectedCategories = categories.filter { !it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        val task = newTasks[position]
        taskViewModel.updateTask(task.id, !task.isSelected)
    }

    private fun onCategorySelected(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        tasksAdapter.setData(tasks, categories)


    }


    private fun deleteTaskFromDatabase(id: Int) {
        taskViewModel.deleteTask(id)
        Log.i("MainActivity", "Task deleted")
    }

    private fun cleanTaskDatabase() {
        taskViewModel.cleanDatabase()
        Log.i("MainActivity", "Database cleaned")
    }
}
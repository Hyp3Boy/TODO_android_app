package com.example.myfirstapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(
    entities = [DataTask::class],
    version = 1
)
@TypeConverters(TaskCategoryConverter::class)
abstract class TaskDatabase : RoomDatabase(){
    abstract val dao: TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = androidx.room.Room.databaseBuilder(
                        context.applicationContext,
                        TaskDatabase::class.java,
                        "task_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
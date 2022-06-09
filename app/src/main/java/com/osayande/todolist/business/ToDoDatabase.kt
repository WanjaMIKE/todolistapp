package com.osayande.roomapp.roomdab

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.osayande.todolist.business.Task
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class ToDoDatabase: RoomDatabase() { //our database class
    abstract fun todoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ToDoDatabase { //ensuring that instance is null before new database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todos_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
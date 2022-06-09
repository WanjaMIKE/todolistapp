package com.osayande.roomapp.roomdab

import androidx.room.*
import com.osayande.todolist.business.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao { //database access object
    @Query("SELECT * FROM todos")
    fun getAllNotes(): Flow<List<Task>> //Fetch all notes from DB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(task: Task)  //insert note in DB

    @Delete
    suspend fun deleteNote(task: Task) //Delete note in DB
}
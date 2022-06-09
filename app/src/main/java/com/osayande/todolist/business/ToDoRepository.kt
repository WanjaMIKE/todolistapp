package com.osayande.todolist.business

import com.osayande.roomapp.roomdab.ToDoDao
import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val toDoDao: ToDoDao) { //this repo acts as an abstraction for the data sources.
        val allNotes: Flow<List<Task>> = toDoDao.getAllNotes()

        suspend fun insertNote(task: Task) {
            toDoDao.insertNote(task)
        }

        suspend fun deleteNote(task: Task) {
                toDoDao.deleteNote(task)
        }
}
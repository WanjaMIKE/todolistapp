package com.osayande.todolist

import android.app.Application
import com.osayande.roomapp.roomdab.ToDoDatabase
import com.osayande.todolist.business.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ToDoApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val tododatabase by lazy { ToDoDatabase.getDatabase(this, applicationScope)}
    val todorepository by lazy { ToDoRepository(tododatabase.todoDao()) }
}
package com.osayande.todolist

import androidx.lifecycle.*
import com.osayande.todolist.business.Task
import com.osayande.todolist.business.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(private val toDoRepository: ToDoRepository): ViewModel() {
    val allnotes: LiveData<List<Task>> = toDoRepository.allNotes.asLiveData()

    //starting a new coroutine scope
    fun insertNote(task: Task) = viewModelScope.launch {
        toDoRepository.insertNote(task) //insert or update note in database
    }

    fun deleteNote(task: Task) = viewModelScope.launch {
        toDoRepository.deleteNote(task) //delete note from database
    }

    fun updateNote(task: Task) = viewModelScope.launch {
        toDoRepository.insertNote(task) //insert or update note in database
    }
}

//A ToDoViewModelFactory ensures that the ViewModel is instantiated correctly.
class ToDoViewModelFactory(private val toDoRepository: ToDoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoViewModel(toDoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class detected")
    }
}
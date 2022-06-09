package com.osayande.todolist.business

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Task(
    //values that will be stored in the database
    var title: String,
    var description: String,
    var complete: Boolean,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id:Int? =null
)

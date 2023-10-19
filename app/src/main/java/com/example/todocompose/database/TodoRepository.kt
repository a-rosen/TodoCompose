package com.example.todocompose.database

import com.example.todocompose.database.models.TodoDataRecord
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val dataFlow : StateFlow<List<TodoDataRecord>>

    fun addItem(todoItem: TodoDataRecord)

    fun deleteItem(id: Long)

    fun updateItem(id: Long, newItemName: String)

    fun toggleCompleted(id: Long)

}
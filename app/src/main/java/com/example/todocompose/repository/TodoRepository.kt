package com.example.todocompose.repository

import com.example.todocompose.repository.models.TodoDataRecord
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val dataFlow : StateFlow<List<TodoDataRecord>>

    suspend fun addItem(todoItem: TodoDataRecord)

    fun deleteItem(id: Long)

    fun updateItem(id: Long, newItemName: String)

    fun toggleCompleted(id: Long)

}
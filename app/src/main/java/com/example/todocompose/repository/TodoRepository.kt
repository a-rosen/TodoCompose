package com.example.todocompose.repository

import com.example.todocompose.repository.models.TodoDataRecord
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val dataFlow : StateFlow<List<TodoDataRecord>>

    suspend fun addItem(todoItem: TodoDataRecord)

    suspend fun deleteItem(todoItem: TodoDataRecord)

    suspend fun updateItemName(id: Long?, newItemName: String)

    suspend fun toggleCompleted(id: Long?)

}
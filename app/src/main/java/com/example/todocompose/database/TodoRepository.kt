package com.example.todocompose.database

import com.example.todocompose.models.TodoItem
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val dataFlow : StateFlow<List<TodoItem>>

    fun addItem(todoItem: TodoItem)

    fun deleteItem(itemId: String)

    fun getAllItems(): List<TodoItem>

    fun getOneItem(itemId: String): TodoItem
}
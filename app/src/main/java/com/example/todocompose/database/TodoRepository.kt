package com.example.todocompose.database

import com.example.todocompose.models.TodoItem

interface TodoRepository {
    fun addItem(todoItem: TodoItem)

    fun deleteItem(itemId: Int)

    fun getAllItems(): List<TodoItem>

    fun getOneItem(itemId: Int): TodoItem
}
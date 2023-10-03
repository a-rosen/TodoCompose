package com.example.todocompose.database

import com.example.todocompose.models.TodoItem

class InMemoryRepository: TodoRepository {
    override fun addItem(todoItem: TodoItem) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun getAllItems(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override fun getOneItem(itemId: Int): TodoItem {
        TODO("Not yet implemented")
    }

}
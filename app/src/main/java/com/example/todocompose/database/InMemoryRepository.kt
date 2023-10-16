package com.example.todocompose.database

import android.util.Log
import com.example.todocompose.models.TodoItem

class InMemoryRepository: TodoRepository {
    val listOfThings: MutableList<TodoItem?> = mutableListOf(null)

    override fun addItem(todoItem: TodoItem) {
        listOfThings.add(todoItem)
        Log.d("annie", "inMemoryrepository listofthigns ${listOfThings}")
    }

    override fun deleteItem(itemId: String) {
        TODO("Not yet implemented")
    }

    override fun getAllItems(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override fun getOneItem(itemId: String): TodoItem {
        TODO("Not yet implemented")
    }

}
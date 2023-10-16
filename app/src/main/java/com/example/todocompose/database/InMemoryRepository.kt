package com.example.todocompose.database

import android.util.Log
import com.example.todocompose.models.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRepository: TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<TodoItem>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    override fun addItem(todoItem: TodoItem) {
        val newList = _internalDataFlow.value + listOf(todoItem)

        _internalDataFlow.update {
            newList
        }

        Log.d("annie", "inMemoryrepository listofthigns ${newList}")
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
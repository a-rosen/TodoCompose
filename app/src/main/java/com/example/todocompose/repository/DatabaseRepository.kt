package com.example.todocompose.repository

import com.example.todocompose.database.dao.TodoDao
import com.example.todocompose.database.models.asTodoEntity
import com.example.todocompose.repository.models.TodoDataRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// NEXT TIME ON DATABASES: don't run on the main thread lol

class DatabaseRepository @Inject constructor(private val todoDao: TodoDao) : TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<TodoDataRecord>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    override fun addItem(todoItem: TodoDataRecord) {
        todoDao.addItem(todoItem.asTodoEntity())
    }

    override fun deleteItem(id: Long) {
        TODO("Not yet implemented")
    }

    override fun updateItem(id: Long, newItemName: String) {
        TODO("Not yet implemented")
    }

    override fun toggleCompleted(id: Long) {
        TODO("Not yet implemented")
    }
}
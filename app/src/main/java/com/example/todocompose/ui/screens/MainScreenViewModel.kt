package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import com.example.todocompose.database.InMemoryRepository
import com.example.todocompose.database.TodoRepository
import com.example.todocompose.models.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel: ViewModel() {
    private val _stateFlow = MutableStateFlow(value = MainScreenState.EMPTY)
    val stateFlow = _stateFlow.asStateFlow()

    var repository: TodoRepository? = InMemoryRepository()

    fun addItem(item: TodoItem) {
        repository?.addItem(item)
        // TODO: where do we update state with the text field's value?
    }

    fun deleteItem(itemId: String) {
        repository?.deleteItem(itemId)
    }

    fun getAllItems() {
        repository?.getAllItems()
    }
 }
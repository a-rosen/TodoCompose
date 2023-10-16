package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.database.InMemoryRepository
import com.example.todocompose.database.TodoRepository
import com.example.todocompose.models.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _internalScreenStateFlow =
        MutableStateFlow<MainScreenState>(value = MainScreenState.EMPTY)
    val screenStateFlow: StateFlow<MainScreenState> = _internalScreenStateFlow.asStateFlow()

    var repository: TodoRepository = InMemoryRepository()

    // init keyword means: run this whenever an instance of this class is constructed
    init {
        viewModelScope.launch {
            repository.dataFlow.collect {
                _internalScreenStateFlow.update { oldValue ->
                    MainScreenState(oldValue.inputText, it)
                }
            }
        }

    }

    fun addItem(item: TodoItem) {
        repository.addItem(item)
    }

    fun deleteItem(itemId: String) {
        repository.deleteItem(itemId)
    }

    fun getAllItems() {
        repository.getAllItems()
    }
}
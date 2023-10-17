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
import kotlin.random.Random

class MainScreenViewModel : ViewModel() {
    private val _internalScreenStateFlow =
        MutableStateFlow<MainScreenState>(value = MainScreenState.EMPTY)
    val screenStateFlow: StateFlow<MainScreenState> = _internalScreenStateFlow.asStateFlow()

    private val repository: TodoRepository = InMemoryRepository()

    // init keyword means: run this whenever an instance of this class is constructed
    init {
        viewModelScope.launch {
            repository.dataFlow.collect {
                _internalScreenStateFlow.update { oldValue ->
                    MainScreenState("", it)
                }
            }
        }
    }

    fun updateInputText(newText: String) {
        _internalScreenStateFlow.update { oldValue ->
            MainScreenState(newText, oldValue.toDoListItems)
        }
    }

    fun onSubmitButtonClick() {
        val newTodoItem = TodoItem(
            id = Random.nextLong(),
            name = _internalScreenStateFlow.value.inputText,
            isChecked = false
        )

        repository.addItem(newTodoItem)
    }
    fun toggleChecked(itemToChange: TodoItem) {
        _internalScreenStateFlow.update { oldValue ->
            val oldListItems = oldValue.toDoListItems

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem == itemToChange) {
                        oldItem.copy(isChecked = !oldItem.isChecked)
                    } else {
                        oldItem
                    }
                }

            MainScreenState(oldValue.inputText, newListItems)
        }
    }
}

//TODO: Get the repository to do this (honestly we will probably just copy and rename as appropriate
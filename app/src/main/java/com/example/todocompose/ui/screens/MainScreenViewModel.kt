package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.database.InMemoryRepository
import com.example.todocompose.database.TodoRepository
import com.example.todocompose.database.models.ItemData
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
        _internalScreenStateFlow.update { oldState ->
            MainScreenState(newText, oldState.toDoListItems)
        }
    }

    fun onSubmitButtonClick() {
        val newTodoItem = ItemData(
            id = Random.nextLong(),
            name = _internalScreenStateFlow.value.inputText,
            completed = false
        )

        repository.addItem(newTodoItem)
    }
    fun toggleChecked(itemToChange: ItemData) {
        repository.toggleCompleted(itemToChange)
    }
}
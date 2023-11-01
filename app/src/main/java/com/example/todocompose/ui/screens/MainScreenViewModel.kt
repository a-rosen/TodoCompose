package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.database.TodoRepository
import com.example.todocompose.database.models.TodoDataRecord
import com.example.todocompose.ui.models.TodoUiItem
import com.example.todocompose.ui.models.asTodoUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _internalScreenStateFlow =
        MutableStateFlow<MainScreenState>(value = MainScreenState.EMPTY)
    val screenStateFlow: StateFlow<MainScreenState> = _internalScreenStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.dataFlow.collect { records ->
                _internalScreenStateFlow.update {
                    MainScreenState(
                        "",
                        records.map { it.asTodoUiItem() }
                    )
                }
            }
        }
    }

    fun updateNewItemInputText(newText: String) {
        _internalScreenStateFlow.update { oldState ->
            MainScreenState(newText, oldState.toDoListItems)
        }
    }

    fun updateItemText(id: Long, newText: String) {
        _internalScreenStateFlow.update { oldState ->
            val oldListItems = oldState.toDoListItems

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem.id == id) {
                        oldItem.copy(name = newText)
                    } else {
                        oldItem
                    }
                }
            return@update MainScreenState(oldState.newItemInputText, newListItems)
        }
    }

    fun onUpdateItemSubmit(itemToUpdate: TodoUiItem) {
        val oldMatchingItem = repository
            .dataFlow
            .value
            .firstOrNull { it.id == itemToUpdate.id } ?: return

        if (oldMatchingItem.name != itemToUpdate.name) {
            repository.updateItem(itemToUpdate.id, itemToUpdate.name)
        } else {
            toggleIsBeingModified(itemToUpdate)
        }
    }

    fun onAddNewItemButtonClick() {
        val newTodoItem = TodoDataRecord(
            id = Random.nextLong(),
            name = _internalScreenStateFlow.value.newItemInputText,
            completed = false
        )
        if (newTodoItem.name == "") {
            return
        } else {
            repository.addItem(newTodoItem)
        }
    }

    fun onDeleteButtonClick(itemToDelete: TodoUiItem) {
        repository.deleteItem(itemToDelete.id)
    }

    fun onEditButtonClick(itemToUpdate: TodoUiItem) {
        _internalScreenStateFlow.update { oldState ->
            val oldListItems = oldState.toDoListItems

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem.id == itemToUpdate.id) {
                        oldItem.copy(isBeingModified = true)
                    } else {
                        oldItem
                    }
                }

            return@update MainScreenState(oldState.newItemInputText, newListItems)
        }
    }

    fun toggleChecked(itemToChange: TodoUiItem) {
        repository.toggleCompleted(itemToChange.id)

        _internalScreenStateFlow.update { oldState ->
            val oldListItems = oldState.toDoListItems

            val newListItems = oldListItems
                .filter { !it.completed } +
                    oldListItems
                        .filter { it.completed }

            return@update MainScreenState(oldState.newItemInputText, newListItems)
        }
    }

    private fun toggleIsBeingModified(itemToUpdate: TodoUiItem) {
        _internalScreenStateFlow.update { oldState ->
            val oldListItems = oldState.toDoListItems

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem.id == itemToUpdate.id) {
                        oldItem.copy(isBeingModified = false)
                    } else {
                        oldItem
                    }
                }

            return@update MainScreenState(oldState.newItemInputText, newListItems)
        }
    }
}
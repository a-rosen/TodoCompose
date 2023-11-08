package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.repository.TodoRepository
import com.example.todocompose.repository.models.TodoDataRecord
import com.example.todocompose.repository.models.asTodoDataRecord
import com.example.todocompose.ui.models.TodoUiItem
import com.example.todocompose.ui.models.asTodoUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// when dagger sees an @inject annotation,
// it will generate code such that, when this viewmodel is requested, it will fill in the
// blanks of the dependencies with generated code
// so, dagger calls the function we wrote in the relevant module

class TodoListScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    private val _internalScreenStateFlow =
        MutableStateFlow<TodoListScreenState>(value = TodoListScreenState.EMPTY)
    val screenStateFlow: StateFlow<TodoListScreenState> = _internalScreenStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .dataFlow
                .collect { records ->
                    _internalScreenStateFlow.update {
                        TodoListScreenState(
                            "",
                            records.map { it.asTodoUiItem() }
                        )
                    }
                }
        }
    }

    fun updateNewItemInputText(newText: String) {
        _internalScreenStateFlow.update { oldState ->
            TodoListScreenState(newText, oldState.toDoListItems)
        }
    }

    fun updateItemText(id: Long?, newText: String) {
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
            return@update TodoListScreenState(oldState.newItemInputText, newListItems)
        }
    }

    fun onUpdateItemSubmit(itemToUpdate: TodoUiItem) {
        val oldMatchingItem = repository
            .dataFlow
            .value
            .firstOrNull { it.id == itemToUpdate.id } ?: return

        if (oldMatchingItem.name != itemToUpdate.name) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.updateItemName(
                    itemToUpdate.asTodoDataRecord().id,
                    itemToUpdate.name
                )
            }
        } else {
            toggleIsBeingModified(itemToUpdate)
        }
    }

    fun onAddNewItemButtonClick() {
        val newTodoItem = TodoDataRecord(
            id = null,
            name = _internalScreenStateFlow.value.newItemInputText,
            completed = false
        )
        if (newTodoItem.name == "") {
            return
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addItem(newTodoItem)
            }
        }
    }

    fun onDeleteButtonClick(itemToDelete: TodoUiItem) {
        val recordToDelete = itemToDelete.asTodoDataRecord()
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(recordToDelete)
        }
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

            return@update TodoListScreenState(oldState.newItemInputText, newListItems)
        }
    }

    fun toggleChecked(itemToChange: TodoUiItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleCompleted(itemToChange.id)

            _internalScreenStateFlow.update { oldState ->
                val oldListItems = oldState.toDoListItems

                val newListItems = oldListItems
                    .filter { !it.completed } +
                        oldListItems
                            .filter { it.completed }

                return@update TodoListScreenState(oldState.newItemInputText, newListItems)
            }
        }

    }

    fun selectAnItem(itemToSelect: TodoUiItem) {
        _internalScreenStateFlow.update { oldState ->
            TodoListScreenState(
                oldState.newItemInputText,
                oldState.toDoListItems,
                selectedItem = itemToSelect
            )
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

            return@update TodoListScreenState(oldState.newItemInputText, newListItems)
        }
    }
}
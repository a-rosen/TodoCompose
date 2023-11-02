package com.example.todocompose.ui.screens

import com.example.todocompose.ui.models.TodoUiItem

data class TodoListScreenState(
    val newItemInputText: String,
    val toDoListItems: List<TodoUiItem>,
    val selectedItem: TodoUiItem? = null
) {
    companion object {
        val EMPTY = TodoListScreenState(
            newItemInputText = "",
            toDoListItems = listOf(),
            selectedItem = null
        )
    }
}

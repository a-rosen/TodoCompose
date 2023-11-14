package com.example.todocompose.ui.screens

import com.example.todocompose.ui.models.TodoUiItem

data class TodoListScreenState(
    val newItemInputText: String,
    val toDoListItems: List<TodoUiItem>,
) {
    companion object {
        val EMPTY = TodoListScreenState(
            newItemInputText = "",
            toDoListItems = listOf(),
        )
    }
}

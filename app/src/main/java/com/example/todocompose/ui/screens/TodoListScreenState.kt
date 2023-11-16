package com.example.todocompose.ui.screens

import com.example.todocompose.ui.models.TodoUiItem

data class TodoListScreenState(
    val newItemInputText: String,
    val toDoListItems: List<TodoUiItem>,
    var itemIsBeingAdded: Boolean,
) {
    companion object {
        val EMPTY = TodoListScreenState(
            newItemInputText = "",
            toDoListItems = listOf(),
            itemIsBeingAdded = false
        )
    }
}

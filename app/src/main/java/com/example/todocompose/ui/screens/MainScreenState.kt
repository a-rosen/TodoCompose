package com.example.todocompose.ui.screens

import com.example.todocompose.ui.models.TodoUiItem

data class MainScreenState(
    val newItemInputText: String,
    val toDoListItems: List<TodoUiItem>,
) {
    companion object {
        val EMPTY = MainScreenState(
            newItemInputText = "",
            toDoListItems = listOf()
        )
    }
}

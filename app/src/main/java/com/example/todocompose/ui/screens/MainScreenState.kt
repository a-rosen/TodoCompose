package com.example.todocompose.ui.screens

import com.example.todocompose.ui.models.TodoUiItem

data class MainScreenState(
    val inputText: String,
    val toDoListItems: List<TodoUiItem>,
) {
    companion object {
        val EMPTY = MainScreenState(
            inputText = "",
            toDoListItems = listOf()
        )
    }
}

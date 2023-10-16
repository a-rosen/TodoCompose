package com.example.todocompose.ui.screens

import com.example.todocompose.models.TodoItem

data class MainScreenState(
    val inputText: String,
    val toDoListItems: List<TodoItem>

) {
    companion object {
        val EMPTY = MainScreenState(
            inputText = "",
            toDoListItems = listOf()
        )
    }
}

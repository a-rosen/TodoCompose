package com.example.todocompose.ui.screens

import com.example.todocompose.database.models.ItemData

data class MainScreenState(
    val inputText: String,
    val toDoListItems: List<ItemData>,

    ) {
    companion object {
        val EMPTY = MainScreenState(
            inputText = "",
            toDoListItems = listOf()
        )
    }
}

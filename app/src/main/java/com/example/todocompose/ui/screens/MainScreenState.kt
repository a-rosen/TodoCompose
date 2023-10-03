package com.example.todocompose.ui.screens

data class MainScreenState(
    val inputText: String,

) {
    companion object {
        val EMPTY = MainScreenState(
            inputText = ""
        )
    }
}

package com.example.todocompose.ui.screens

import com.example.todocompose.repository.models.TodoDataRecord

data class DetailScreenState(
    val currentItemData: TodoDataRecord?,
) {
    companion object {
        val EMPTY = DetailScreenState(
            currentItemData = TodoDataRecord(
                id = null,
                name = "",
                completed = false
            )
        )
    }
}
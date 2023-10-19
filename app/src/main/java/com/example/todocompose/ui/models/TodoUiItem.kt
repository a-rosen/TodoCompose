package com.example.todocompose.ui.models

import com.example.todocompose.database.models.TodoDataRecord

data class TodoUiItem(
    val id: Long,
    val name: String,
    val completed: Boolean,
    val isBeingModified: Boolean,
)

fun TodoDataRecord.asTodoUiItem() = TodoUiItem(
    this.id,
    this.name,
    this.completed,
    isBeingModified = false
)
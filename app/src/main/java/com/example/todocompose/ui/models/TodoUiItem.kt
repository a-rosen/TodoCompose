package com.example.todocompose.ui.models

import com.example.todocompose.repository.models.TodoDataRecord

data class TodoUiItem(
    val id: Long,
    var name: String,
    var completed: Boolean,
    var isBeingModified: Boolean,
)

fun TodoDataRecord.asTodoUiItem() = TodoUiItem(
    this.id,
    this.name,
    this.completed,
    isBeingModified = false
)
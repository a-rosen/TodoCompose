package com.example.todocompose.repository.models

import com.example.todocompose.ui.models.TodoUiItem

data class TodoDataRecord(
    var id: Long?,
    val name: String,
    val completed: Boolean
)

fun TodoUiItem.asTodoDataRecord() = TodoDataRecord(
    this.id,
    this.name,
    this.completed,
)
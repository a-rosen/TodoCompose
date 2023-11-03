package com.example.todocompose.repository.models

import com.example.todocompose.database.models.TodoEntity

data class TodoDataRecord(
    val id: Long,
    val name: String,
    val completed: Boolean
)

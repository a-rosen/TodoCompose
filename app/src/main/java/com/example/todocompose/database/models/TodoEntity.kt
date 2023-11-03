package com.example.todocompose.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todocompose.repository.models.TodoDataRecord
import com.example.todocompose.ui.models.TodoUiItem

@Entity
data class TodoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val completed: Boolean,
)
fun TodoDataRecord.asTodoEntity() = TodoEntity(
    this.id,
    this.name,
    this.completed,
)
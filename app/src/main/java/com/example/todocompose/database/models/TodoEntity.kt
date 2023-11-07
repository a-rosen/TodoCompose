package com.example.todocompose.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todocompose.repository.models.TodoDataRecord

@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val completed: Boolean,
)

fun TodoDataRecord.asTodoEntity() = TodoEntity(
    this.id,
    this.name,
    this.completed,
)

fun TodoEntity.asTodoDataRecord() = TodoDataRecord(
    this.id!!,
    this.name,
    this.completed,
)
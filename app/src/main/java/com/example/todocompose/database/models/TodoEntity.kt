package com.example.todocompose.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val completed: Boolean,
)
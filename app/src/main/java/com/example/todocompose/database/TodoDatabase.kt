package com.example.todocompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.database.dao.TodoDao
import com.example.todocompose.database.models.TodoEntity

@Database(
    entities = [TodoEntity::class], version = 2
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
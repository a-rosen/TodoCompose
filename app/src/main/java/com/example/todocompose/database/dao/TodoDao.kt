package com.example.todocompose.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.todocompose.database.models.TodoEntity

@Dao
interface TodoDao {
    @Insert
    fun addItem(todoItem: TodoEntity)

    @Delete
    fun deleteItem(id: Long)

    @Update
    fun updateItem(id: Long, newItemName: String)
    // TODO: syntax for this probably means different arguments, investigate

    @Update
    fun toggleCompleted(id: Long)
    // TODO: see above
}
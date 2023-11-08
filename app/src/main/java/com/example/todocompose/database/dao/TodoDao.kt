package com.example.todocompose.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todocompose.database.models.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo ORDER BY id")
    fun getFlowOfAllItems() : Flow<List<TodoEntity>>

    @Insert
    fun addItem(todoItem: TodoEntity)

    @Delete
    fun deleteItem(todoItem: TodoEntity)

    @Query("UPDATE todo SET name = :newItemName WHERE id = :id")
    fun updateItem(
        id: Long?,
        newItemName: String
    )

    @Query("UPDATE todo SET completed = NOT completed WHERE id = :id")
    fun toggleCompleted(id: Long)
}
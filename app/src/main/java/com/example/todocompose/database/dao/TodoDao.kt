package com.example.todocompose.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todocompose.database.models.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todoentity ORDER BY name")
    fun getFlowOfAllItems() : Flow<List<TodoEntity>>

    @Insert
    fun addItem(todoItem: TodoEntity)

    @Delete
    fun deleteItem(todoItem: TodoEntity)

//    @Update
//    fun updateItem(id: Long, newItemName: String)
//    // TODO: syntax for this probably means different arguments, investigate
//
//    @Update
//    fun toggleCompleted(id: Long)
//    // TODO: see above
}
package com.example.todocompose.database

import com.example.todocompose.database.models.ItemData
import kotlinx.coroutines.flow.StateFlow

interface TodoRepository {
    val dataFlow : StateFlow<List<ItemData>>

    fun addItem(todoItem: ItemData)

    fun deleteItem(todoItem: ItemData)

    fun getAllItems(): List<ItemData>

    fun getOneItem(itemId: String): ItemData

    fun toggleCompleted(todoItem: ItemData)
}
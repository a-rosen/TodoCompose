package com.example.todocompose.database

import android.util.Log
import com.example.todocompose.database.models.TodoDataRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRepository : TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<TodoDataRecord>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    override fun addItem(todoItem: TodoDataRecord) {
        val newList = _internalDataFlow.value + listOf(todoItem)

        _internalDataFlow.update {
            newList
        }
    }

    override fun deleteItem(id: Long) {
        val newList = _internalDataFlow.value
            .filter { it.id != id }

        _internalDataFlow.update {
            newList
        }
    }

    override fun updateItem(id: Long, newItemName: String) {
        Log.d("annie", "in repo updateItem before flow: id is $id, newIN is $newItemName")

        _internalDataFlow.update { oldState ->
            Log.d("annie", "in repo updateItem: id is $id, newIN is $newItemName")
            val oldListItems = _internalDataFlow.value

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem.id == id) {
                        oldItem.copy(name = newItemName)
                    } else {
                        oldItem
                    }
                }
            return@update newListItems
        }
    }

    override fun toggleCompleted(id: Long) {
        _internalDataFlow.update { oldState ->
            val oldListItems = _internalDataFlow.value

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem.id == id) {
                        oldItem.copy(completed = !oldItem.completed)
                    } else {
                        oldItem
                    }
                }

            return@update newListItems
        }
    }

}
package com.example.todocompose.database

import com.example.todocompose.models.TodoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRepository : TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<TodoItem>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    override fun addItem(todoItem: TodoItem) {
        val newList = _internalDataFlow.value + listOf(todoItem)

        _internalDataFlow.update {
            newList
        }
    }

    override fun deleteItem(itemId: String) {
        TODO("Not yet implemented")
    }

    override fun getAllItems(): List<TodoItem> {
        TODO("Not yet implemented")
    }

    override fun getOneItem(itemId: String): TodoItem {
        TODO("Not yet implemented")
    }

    override fun toggleCheckedItem(todoItem: TodoItem) {
        _internalDataFlow.update { oldState ->
            val oldListItems = _internalDataFlow.value

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem == todoItem) {
                        oldItem.copy(isChecked = !oldItem.isChecked)
                    } else {
                        oldItem
                    }
                }

            return@update newListItems
        }
    }

}
package com.example.todocompose.database

import com.example.todocompose.database.models.ItemData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRepository : TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<ItemData>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    override fun addItem(todoItem: ItemData) {
        val newList = _internalDataFlow.value + listOf(todoItem)

        _internalDataFlow.update {
            newList
        }
    }

    override fun deleteItem(todoItem: ItemData) {
        val newList = _internalDataFlow.value
            .filter { it != todoItem }

        _internalDataFlow.update {
            newList
        }
    }

    override fun getAllItems(): List<ItemData> {
        TODO("Not yet implemented")
    }

    override fun getOneItem(itemId: String): ItemData {
        TODO("Not yet implemented")
    }

    override fun toggleCompleted(todoItem: ItemData) {
        _internalDataFlow.update { oldState ->
            val oldListItems = _internalDataFlow.value

            val newListItems = oldListItems
                .map { oldItem ->
                    if (oldItem == todoItem) {
                        oldItem.copy(completed = !oldItem.completed)
                    } else {
                        oldItem
                    }
                }

            return@update newListItems
        }
    }

}
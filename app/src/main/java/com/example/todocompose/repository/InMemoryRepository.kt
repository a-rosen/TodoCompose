package com.example.todocompose.repository

import com.example.todocompose.repository.models.TodoDataRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * An implementation of the Repository interface that doesn't save anything long-term, it
 * only works while the app is alive. If you kill the app, it forgets everything.
 */
class InMemoryRepository @Inject constructor() : TodoRepository {
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

        _internalDataFlow.update {
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
        _internalDataFlow.update {
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
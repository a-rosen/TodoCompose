package com.example.todocompose.repository

import com.example.todocompose.database.dao.TodoDao
import com.example.todocompose.database.models.asTodoDataRecord
import com.example.todocompose.database.models.asTodoEntity
import com.example.todocompose.repository.models.TodoDataRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val todoDao: TodoDao,
    private val repositoryScope: CoroutineScope
) : TodoRepository {
    private val _internalDataFlow = MutableStateFlow<List<TodoDataRecord>>(value = listOf())
    override val dataFlow = _internalDataFlow.asStateFlow()

    init {
        repositoryScope.launch(Dispatchers.IO) {
            todoDao
                .getFlowOfAllItems()
                .flowOn(Dispatchers.IO)
                .map { listOfEntities ->
                    val listOfRecords = listOfEntities.map { entity ->
                        return@map entity.asTodoDataRecord()
                    }
                    return@map listOfRecords
                }
                .collect { listOfRecords ->
                    _internalDataFlow.update { listOfRecords }
                }
        }
    }

    override suspend fun addItem(todoItem: TodoDataRecord) {
        todoDao.addItem(todoItem.asTodoEntity())
    }

    override suspend fun deleteItem(todoItem: TodoDataRecord) {
        todoDao.deleteItem(todoItem.asTodoEntity())
    }

    override suspend fun updateItemName(id: Long?, newItemName: String) {
        todoDao.updateItem(id, newItemName = newItemName)
    }

    override suspend fun toggleCompleted(id: Long?) {
        if (id != null) {
            todoDao.toggleCompleted(id)
        }
    }

    override suspend fun getItemById(id: Long?): TodoDataRecord {
        return todoDao.getItemById(id)
    }
}
package com.example.todocompose.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.repository.TodoRepository
import com.example.todocompose.ui.models.TodoUiItem
import com.example.todocompose.ui.models.asTodoUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: TodoRepository,
    detailSavedStateHandle: SavedStateHandle
) : ViewModel() {
    private val itemId: Long =
        checkNotNull(detailSavedStateHandle[DetailsDestination.itemIdArg])

    fun getItemById() : TodoUiItem {
        viewModelScope.launch(Dispatchers.IO) {
            return@launch repository.getItemById(itemId).asTodoUiItem()
        }
    }
}
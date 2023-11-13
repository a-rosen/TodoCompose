package com.example.todocompose.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.repository.TodoRepository
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

    fun getItemById() {
        viewModelScope.launch(Dispatchers.IO) {
           repository.getItemById(itemId)
        }
    }

}
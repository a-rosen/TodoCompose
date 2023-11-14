package com.example.todocompose.ui.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocompose.repository.TodoRepository
import com.example.todocompose.ui.models.TodoUiItem
import com.example.todocompose.ui.models.asTodoUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: TodoRepository,
    detailSavedStateHandle: SavedStateHandle
) : ViewModel() {
    private val itemId: Long =
        checkNotNull(detailSavedStateHandle[DetailsDestination.itemIdArg])

    private val _internalDetailScreenStateFlow =
        MutableStateFlow<DetailScreenState>(value = DetailScreenState.EMPTY)
    val detailScreenStateFlow: StateFlow<DetailScreenState> =
        _internalDetailScreenStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .dataFlow
                .collect { records ->
                    _internalDetailScreenStateFlow.update {
                        DetailScreenState(
                            records.find { it.id == itemId }
                        )
                    }
                }
        }
    }

    fun getItemById(): TodoUiItem? {
        return _internalDetailScreenStateFlow.value.currentItemData?.asTodoUiItem()
    }
}
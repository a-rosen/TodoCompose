package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import com.example.todocompose.database.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel: ViewModel() {
    private val _stateFlow = MutableStateFlow(value = MainScreenState.EMPTY)
    val stateFlow = _stateFlow.asStateFlow()

    var repository: TodoRepository? = null
}
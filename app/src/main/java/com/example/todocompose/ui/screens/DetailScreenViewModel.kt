package com.example.todocompose.ui.screens

import androidx.lifecycle.ViewModel
import com.example.todocompose.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

}
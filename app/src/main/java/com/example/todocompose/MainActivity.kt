package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todocompose.ui.screens.DetailScreen
import com.example.todocompose.ui.screens.DetailScreenViewModel
import com.example.todocompose.ui.screens.TodoListScreen
import com.example.todocompose.ui.screens.TodoListScreenViewModel
import com.example.todocompose.ui.theme.TodoComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val listViewModel: TodoListScreenViewModel by viewModels()
    private val detailViewModel: DetailScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoComposeTheme {
                val listScreenState by listViewModel.screenStateFlow.collectAsState()


                if (listScreenState.selectedItem == null) {
                    TodoListScreen(
                        state = listScreenState,
                        viewModel = listViewModel,
                    )
                } else {
                    DetailScreen(
                        viewModel = detailViewModel,
                        item = listScreenState.selectedItem!!
                    )
                }
            }
        }
    }
}
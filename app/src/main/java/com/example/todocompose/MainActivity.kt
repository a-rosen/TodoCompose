package com.example.todocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.todocompose.ui.screens.MainScreen
import com.example.todocompose.ui.screens.MainScreenViewModel
import com.example.todocompose.ui.theme.TodoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainScreenViewModel by viewModels()

        setContent {
            TodoComposeTheme {
                val mainScreenState by viewModel.screenStateFlow.collectAsState()

                MainScreen(
                    state = mainScreenState,
                    viewModel = viewModel,
                )
            }
        }

    }
}

// a list of items
// each item has a checkbox next to it
// the checkbox should be able to be checked and unchecked; appearance of item should change when this happens
// interface for adding items to the list and removing items from the list
// items need to persist through invocations of the app, need to be stored someplace



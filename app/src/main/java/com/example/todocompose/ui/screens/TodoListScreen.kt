package com.example.todocompose.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import com.example.todocompose.repository.InMemoryRepository
import com.example.todocompose.ui.components.InputField
import com.example.todocompose.ui.components.ListItemCard
import com.example.todocompose.ui.models.TodoUiItem

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class
)

@Composable
fun TodoListScreen(
    viewModel: TodoListScreenViewModel,
    navigateToDetailScreen: (TodoUiItem) -> Unit
) {
    val listScreenState by viewModel.screenStateFlow.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "LIST OF THINGS TO DO",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    "THIS IS THE BOTTOM BAR!",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f, true)
                    .clickable { keyboardController?.hide() }
            ) {
                items(listScreenState.toDoListItems) { item ->
                    ListItemCard(
                        item = item,
                        onBoxClicked = { viewModel.toggleChecked(item) },
                        onDropdownIconClicked = { viewModel.toggleDropdown(item) }
                    )
                }
            }
            InputField(
                onSubmit = {
                    viewModel.onAddNewItemButtonClick()
                },
                onInputValueChange = {
                    viewModel.updateNewItemInputText(it)
                },
                displayedText = listScreenState.newItemInputText,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(
) {
    TodoListScreen(
        navigateToDetailScreen = {},
        viewModel = TodoListScreenViewModel(
            repository = InMemoryRepository(),
            savedStateHandle = SavedStateHandle()
        )
    )
}

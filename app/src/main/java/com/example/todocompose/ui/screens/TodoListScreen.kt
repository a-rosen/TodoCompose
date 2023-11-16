package com.example.todocompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.repository.InMemoryRepository
import com.example.todocompose.ui.components.ListItemCard
import com.example.todocompose.ui.models.TodoUiItem

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class
)

@Composable
fun TodoListScreen(
    viewModel: TodoListScreenViewModel,
    navigateToDetailScreen: (TodoUiItem) -> Unit,
) {
    val listScreenState by viewModel.screenStateFlow.collectAsState()
    val itemIsBeingAdded = listScreenState.itemIsBeingAdded

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
        floatingActionButton = {
            if (!itemIsBeingAdded) {
                FloatingActionButton(
                    onClick = { viewModel.toggleItemBeingAdded() },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "add task button",
                        )
                    },
                )
            } else {
                FloatingActionButton(
                    onClick = {
                        viewModel.onAddNewItemButtonClick()
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "submit task button"
                        )
                    },
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f, true)
            ) {
                items(listScreenState.toDoListItems) { item ->
                    ListItemCard(
                        item = item,
                        onBoxClicked = { viewModel.toggleChecked(item) },
                        onDropdownIconClicked = { viewModel.toggleDropdown(item) },
                        onDeleteClicked = { viewModel.onDeleteButtonClick(item) },
                        onEditClicked = { viewModel.onEditButtonClick(item) },
                        onEditSubmitted = { viewModel.onUpdateItemSubmit(item) },
                        onDetailsClicked = { navigateToDetailScreen(item) },
                        onItemTextChanged = { viewModel.updateItemText(item.id, it) }
                    )
                }
            }
            if (itemIsBeingAdded) {
                OutlinedTextField(
                    value = listScreenState.newItemInputText,
                    onValueChange = { viewModel.updateNewItemInputText(it) },
                    label = { Text("Task") },
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
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
        ),
    )
}

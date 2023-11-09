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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todocompose.ui.components.InputField
import com.example.todocompose.ui.components.ItemWithCheckbox

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class
)

@Composable
fun TodoListScreen(
    viewModel: TodoListScreenViewModel = viewModel()
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
                )
            )
        },
        bottomBar = {
            BottomAppBar {
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
                    ItemWithCheckbox(
                        item = item,
                        onBoxClicked = { viewModel.toggleChecked(item) },
                        onDeleteClicked = { viewModel.onDeleteButtonClick(item) },
                        onEditClicked = { viewModel.onEditButtonClick(item) },
                        onDetailsClicked = { viewModel.selectAnItem(item) },
                        onItemTextChanged = { viewModel.updateItemText(item.id, it) },
                        onEditSubmitted = { viewModel.onUpdateItemSubmit(item) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(
) {
    TodoListScreen()
}

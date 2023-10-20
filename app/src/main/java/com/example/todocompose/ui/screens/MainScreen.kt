package com.example.todocompose.ui.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.ui.components.InputField
import com.example.todocompose.ui.components.ItemWithCheckbox
import com.example.todocompose.ui.models.TodoUiItem


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    state: MainScreenState,
    viewModel: MainScreenViewModel,
) {
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
                        viewModel.onSubmitButtonClick()
                    },
                    onInputValueChange = {
                        viewModel.updateInputText(it)
                    },
                    displayedText = state.inputText,
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
            LazyColumn {
                items(state.toDoListItems) { item ->
                    ItemWithCheckbox(
                        item = item,
                        onBoxClicked = {
                            viewModel.toggleChecked(item)
                        },
                        onDeleteClicked = {
                            viewModel.onDeleteButtonClick(item)
                        },
                        onEditClicked = {
                            viewModel.onEditButtonClick(item)
                        },
                        onTextChanged = {
                            viewModel.updateItemText(item.id, it)
                        },
                        onEditSubmitted = {
                            viewModel.onUpdateSubmit(item)
                        }
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
    MainScreen(
        MainScreenState(
            "inputText",
            listOf(
                TodoUiItem(
                    1234L,
                    "name1",
                    false,
                    isBeingModified = false
                ),
                TodoUiItem(
                    5678L,
                    "name2",
                    false,
                    isBeingModified = false
                ),
                TodoUiItem(
                    6743L,
                    "name3",
                    true,
                    isBeingModified = false
                )
            )
        ),
        MainScreenViewModel(),
    )
}

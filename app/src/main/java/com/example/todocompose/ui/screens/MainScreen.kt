package com.example.todocompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todocompose.models.TodoItem
import com.example.todocompose.ui.components.InputField
import com.example.todocompose.ui.components.ItemWithCheckbox


@Composable
fun MainScreen(
    state: MainScreenState,
    viewModel: MainScreenViewModel,
    listItems: List<TodoItem>
) {
    Column {
        InputField(
            onSubmit = {
                viewModel.onSubmitButtonClick()
            },
            onInputValueChange = {
                viewModel.updateInputText(it)
            },
            displayedText = state.inputText
        )

        LazyColumn {
            items(listItems) { item ->
                ItemWithCheckbox(item = item)
            }
        }
    }


}

@Preview
@Composable
fun MainScreenPreview(
) {
    MainScreen(
        MainScreenState("inputText", listOf()),
        MainScreenViewModel(),
        listOf(
            TodoItem(
                1234L,
                "name1",
                false
            ),
            TodoItem(
                5678L,
                "name2",
                false
            ),
            TodoItem(
                6743L,
                "name3",
                true
            )
        )
    )
}
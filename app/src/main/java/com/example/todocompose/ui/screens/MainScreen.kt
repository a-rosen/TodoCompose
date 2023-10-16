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
                viewModel.addItem(
                    TodoItem(
                        id = "todo",
                        name = state.inputText,
                        isChecked = false
                    )
                )
            }
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
                "id1",
                "name1",
                false
            ),
            TodoItem(
                "id2",
                "name2",
                false
            ),
            TodoItem(
                "id3",
                "name3",
                true
            )
        )
    )
}
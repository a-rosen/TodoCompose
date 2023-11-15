package com.example.todocompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.ui.models.TodoUiItem
import androidx.compose.material3.DropdownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListItemCard(
    item: TodoUiItem,
    onBoxClicked: () -> Unit,
    onDropdownIconClicked: () -> Unit,
) {
    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .padding(
                horizontal = 6.dp,
                vertical = 2.dp
            )
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            headlineText = {
                Text(
                    text = item.name,
                    textDecoration = if (item.completed) {
                        TextDecoration.LineThrough
                    } else {
                        null
                    }
                )
            },
            leadingContent = {
                Checkbox(
                    checked = item.completed,
                    onCheckedChange = { onBoxClicked() },
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "more actions",
                    modifier = Modifier
                        .clickable(onClick = onDropdownIconClicked)
                )
                if (item.shouldShowDropdown) {
                    DropdownMenu(
                        expanded = true,
                        onDismissRequest = { onDropdownIconClicked() }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Details") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Edit") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Delete") },
                            onClick = {}
                        )
                    }
                }
            }
        )
    }


}

@Preview
@Composable
fun TodoListItemPreview() {
    ListItemCard(
        item = TodoUiItem(
            id = 1234,
            name = "This is my name",
            completed = false,
            isBeingModified = false,
            shouldShowDropdown = false
        ),
        onBoxClicked = {},
        onDropdownIconClicked = {},
    )
}
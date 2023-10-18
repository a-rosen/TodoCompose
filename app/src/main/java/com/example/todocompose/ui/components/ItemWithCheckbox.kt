package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.database.models.ItemData

@Composable
fun ItemWithCheckbox(
    item: ItemData,
    onBoxClicked: () -> Unit,
) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = item.completed,
                onValueChange = { onBoxClicked() },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

        ) {
        Checkbox(
            checked = item.completed,
            onCheckedChange = null,
        )
        Text(
            text = item.name,
            style = TextStyle(
                textDecoration = if (item.completed) {
                    TextDecoration.LineThrough
                } else {
                    null
                }
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(2f)
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "delete",

            )
    }
}

@Preview
@Composable
fun ItemWithCheckboxPreview() {
    ItemWithCheckbox(
        item = ItemData(
            8974213L, "this is my name", completed = true
        ),
        onBoxClicked = {}
    )

}
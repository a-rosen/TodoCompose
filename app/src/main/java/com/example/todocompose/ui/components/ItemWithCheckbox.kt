package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todocompose.models.TodoItem

@Composable
fun ItemWithCheckbox(
    item: TodoItem,
    onBoxClicked: () -> Unit,
) {

    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = item.isChecked,
                onValueChange = { onBoxClicked() },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),

        verticalAlignment = Alignment.CenterVertically,

        ) {
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = null,
        )
        Text(
            text = item.name,
            // can i incorporate theming into this? this into the theme?
            style = TextStyle(
                textDecoration = if (item.isChecked) {
                    TextDecoration.LineThrough
                } else {
                    null
                }
            ),
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun ItemWithCheckboxPreview() {
    ItemWithCheckbox(
        item = TodoItem(
            8974213L, "this is my name", isChecked = true
        ),
        onBoxClicked = {}
    )

}
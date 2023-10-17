package com.example.todocompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    onSubmit: () -> Unit,
    onInputValueChange: (String) -> Unit,
    displayedText: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = displayedText,
            onValueChange = { onInputValueChange(it) },
            label = { Text("ADD UR ITEM LOL") },
            modifier = modifier
                .background(MaterialTheme.colorScheme.primaryContainer, RectangleShape)
        )

        TextButton(
            onClick = { onSubmit() },
            modifier = modifier
                .background(MaterialTheme.colorScheme.secondary, RectangleShape)
                .weight(2f)
        ) {
            Text(
                text = "ADD",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }

}

@Preview
@Composable
fun InputFieldPreview() {
    InputField(onSubmit = {}, onInputValueChange = {}, displayedText = "Preview")
}
package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    onSubmit: () -> Unit,
    onInputValueChange: (String) -> Unit,
    displayedText: String
) {
    Row {
        TextField(
            value = displayedText,
            onValueChange = { onInputValueChange(it) },
            label = { Text("ADD UR ITEM LOL") }
        )

        TextButton(
            onClick = { onSubmit() }
        ) {
            Text(text = "Click meeee")
        }
    }

}

@Preview
@Composable
fun InputFieldPreview() {
    InputField(onSubmit = {}, onInputValueChange = {}, displayedText = "Preview")
}
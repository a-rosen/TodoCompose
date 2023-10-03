package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    onSubmit: () -> Unit
) {
    Row {
        var text by remember { mutableStateOf("Texty text") }

        TextField(
            value = text,
            onValueChange = { text = it },
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
    InputField(onSubmit = {})
}
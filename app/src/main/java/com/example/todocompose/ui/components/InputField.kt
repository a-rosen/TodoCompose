package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable

fun InputField(
    onSubmit: () -> Unit,
    onInputValueChange: (String) -> Unit,
    displayedText: String,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(start = 4.dp)
    ) {
        OutlinedTextField(
            value = displayedText,
            onValueChange = { onInputValueChange(it) },
            label = { Text("Task") },
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        )

        ElevatedButton(
            onClick = {
                onSubmit()
                keyboardController?.hide()
            },
            modifier = modifier
                .wrapContentSize(),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(),
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp)
        ) {
            Text(
                text = "+",
            )
        }
    }

}

@Preview
@Composable
fun InputFieldPreview() {
    InputField(onSubmit = {}, onInputValueChange = {}, displayedText = "Preview")
}
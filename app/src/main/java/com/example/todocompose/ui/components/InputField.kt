package com.example.todocompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    onSubmit: () -> Unit,
    onInputValueChange: (String) -> Unit,
    displayedText: String,
    modifier: Modifier = Modifier
) {
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
        )

        ElevatedButton(
            onClick = { onSubmit() },
            modifier = modifier
                .padding(start = 4.dp),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(),
            elevation = ButtonDefaults.elevatedButtonElevation(4.dp)
        ) {
            Text(
                text = "+",
                textAlign = TextAlign.Left
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun InputFieldPreview() {
    InputField(onSubmit = {}, onInputValueChange = {}, displayedText = "Preview")
}
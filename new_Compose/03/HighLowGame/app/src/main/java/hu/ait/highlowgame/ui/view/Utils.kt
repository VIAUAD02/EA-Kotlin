package hu.ait.highlowgame.ui.view

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

@Composable
fun DecimalOutlinedTextField(
    onChange: (String) -> Unit
){
    var inputText by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(value = inputText,
        isError = isError,
        label = {
            Text(text = "Enter your guess here")
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        supportingText = {
            if (isError) {
                Text(text = "Error")
            }
        },
        onValueChange = {
            inputText = it
            if (inputText.isEmpty()) {
                isError = false
            }
            if (!inputText.isDigitsOnly()) {
                isError = true
            }
            onChange(inputText)
        },
        trailingIcon = {
            if (isError)
                Icon(
                    Icons.Filled.Warning,
                    "error", tint = MaterialTheme.colorScheme.error)
        }

    )
}
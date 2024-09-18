package hu.ait.composestatedemo.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun MainScreen() {
    val context = LocalContext.current

    var nameText by remember{mutableStateOf("")}
    var showText by remember{mutableStateOf(false)}

    Column {
        TextField(value = nameText,
            onValueChange = {
                nameText = it
            })
        
        Button(onClick = {
            Toast.makeText(context, nameText, Toast.LENGTH_LONG).show()
            showText = !showText
        }) {
            Text(text = "Show")
        }

        if (showText) {
            Text(text = nameText)
        }
    }
}
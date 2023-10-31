package hu.bme.aut.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import hu.bme.aut.composedemo.ui.theme.ComposeDemoTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyTinderProfile()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTinderProfile() {
    var name by remember{mutableStateOf("Ramber")}
    var inputName by remember{mutableStateOf("")}

    Column {
        Text(text = "Name: $inputName", fontSize = 28.sp)
        Text(text = "Height: 194cm")
        Text(text = "Weight: 68kg")

        OutlinedTextField(
            label = { Text(text = "Enter name here:")},
            value = inputName,
            onValueChange = {
                inputName = it
            },
            modifier = Modifier.fillMaxWidth())

        Button(onClick = {
            //name = UUID.randomUUID().toString()
            name = inputName
        }) {
            Text(text = "Save")
        }

    }
}

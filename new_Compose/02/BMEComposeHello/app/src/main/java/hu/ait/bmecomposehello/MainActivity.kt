package hu.ait.bmecomposehello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.bmecomposehello.ui.theme.BMEComposeHelloTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMEComposeHelloTheme {
                Greeting(
                    name = "Android BME"
                )

            }
        }
    }
}

@Composable
fun Greeting(name: String

) {
    var textData by remember { mutableStateOf("Data") }


    Column {
        Text(
            text = "Hello $name!"
        )
        Text(
            text = "$textData"
        )

        Button(onClick = {
            textData = Date(System.currentTimeMillis()).toString()
        }) {
            Text("Show")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMEComposeHelloTheme {
        Greeting("Android")
    }
}
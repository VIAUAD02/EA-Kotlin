package hu.ait.sideeffectdemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.sideeffectdemo.ui.theme.SideEffectDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SideEffectDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisposableDemo()
                    //GreetingWithLaunchedEffect()
                    //Counter()
                    //DisposableScreen()
                }
            }
        }
    }

}

@Composable
fun Counter() {
    // Define a state variable for the count
    val count = remember { mutableStateOf(0) }

    // Use SideEffect to log the current value of count
    SideEffect {
        // Called on every recomposition
        Log.d("DEMO_SIDEEFFECT","SideEffect called")
    }

    Column {
        Button(onClick = { count.value++ }) {
            Text("Increase Count")
        }

        // With every state update, text is changed and recomposition is triggered
        Text("Counter ${count.value}")
    }
}

@Composable
fun GreetingWithLaunchedEffect() {
    var state by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(key1 = state) {
        Toast.makeText(context, "Hello", Toast.LENGTH_LONG).show()
    }


    Column {
        Button(onClick = {
            state = !state
        }) {
            Text(text = "Press me")
        }
    }
}


@Composable
fun DisposableScreen() {
    val context = LocalContext.current

    DisposableEffect(key1 = Unit) {
        Toast.makeText(context, "Enter to composition",
            Toast.LENGTH_LONG).show()

        onDispose {
            Toast.makeText(context, "Leave composition",
                Toast.LENGTH_LONG).show()
        }
    }

    Column {
        Text(text = "Content...")
    }
}

@Composable
fun DisposableDemo() {
    var isVisible by remember { mutableStateOf(true) }

    Column {
        Button(onClick = {
            isVisible = !isVisible
        }) {
            if (isVisible) {
                Text(text = "Hide")
            } else {
                Text(text = "Show")
            }
        }

        if (isVisible) {
            DisposableScreen()
        }
    }
}



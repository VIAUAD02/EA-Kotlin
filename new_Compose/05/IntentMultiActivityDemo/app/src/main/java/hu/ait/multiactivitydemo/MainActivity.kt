package hu.ait.multiactivitydemo

import android.content.Intent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import hu.ait.multiactivitydemo.ui.theme.MultiActivityDemoTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiActivityDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column (
        modifier = modifier
    ) {
        Button(onClick = {
            val intentDetails = Intent()
            intentDetails.setClass(
                context, DetailsActivity::class.java
            )
            intentDetails.putExtra(MainActivity.KEY_DATA, "121")
            context.startActivity(intentDetails)

            // remove MainActivity from BackStack
            //(context as MainActivity).finish()

        }) {
            Text(text = "Show details")
        }
    }
}

package hu.ait.composenavdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hu.ait.composenavdemo.ui.theme.ComposeNavDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }

}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "mainscreen"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("mainscreen") {
            MainScreen(
                onNavigateToDetails = {
                    navController.navigate("detailsscreen") }
            )
        }

        composable("detailsscreen") { DetailsScreen(
            onEggClick = { navController.navigate("eastereggscreen") }
        ) }

        composable("eastereggscreen") { EasterEggSceern() }
    }
}

@Composable
fun MainScreen(onNavigateToDetails: () -> Unit) {
    Column {
        Text(text = "MAIN")
        Button(onClick = {
            onNavigateToDetails()
        }) {
            Text(text = "Show details")
        }
    }
}

@Composable
fun DetailsScreen(onEggClick: () -> Unit) {
    Column {
        Text(text = "DETAILS")
        Text(text = "DETAILS")
        Text(text = "DETAILS",
            modifier = Modifier.clickable {
                onEggClick()
            }
        )
    }
}

@Composable
fun EasterEggSceern() {
    Column {
        Text(text = "EASTEREGG")
    }
}
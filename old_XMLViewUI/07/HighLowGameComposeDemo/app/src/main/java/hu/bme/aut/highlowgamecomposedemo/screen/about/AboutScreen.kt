package hu.bme.aut.highlowgamecomposedemo.screen.about

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    aboutText: String = "Demo app for Jetpack Compose"
) {
    Text(text = "$aboutText")
}
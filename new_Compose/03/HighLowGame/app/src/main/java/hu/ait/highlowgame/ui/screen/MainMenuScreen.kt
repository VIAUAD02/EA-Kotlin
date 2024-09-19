package hu.ait.highlowgame.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MainMenuScreen(
    onStartClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            onStartClick()
        }) {
            Text(text = "Start")
        }
        Button(onClick = { }) {
            Text(text = "Help")
        }
        Button(onClick = { }) {
            Text(text = "About")
        }
    }
}
package hu.bme.aut.retrofithttpdemos.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    onNasaMarsAPISelected: () -> Unit,
    onMoneyAPISelected: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onNasaMarsAPISelected() }) {
            Text(text = "Nasa Mars API")
        }
        Button(onClick = { onMoneyAPISelected() }) {
            Text(text = "Money exchange rates")
        }
    }
}

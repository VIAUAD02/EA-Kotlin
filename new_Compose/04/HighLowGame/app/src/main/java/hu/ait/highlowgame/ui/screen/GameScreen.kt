package hu.ait.highlowgame.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.ait.highlowgame.R
import hu.ait.highlowgame.ui.view.DecimalOutlinedTextField
import java.util.Random

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
) {
    var context = LocalContext.current

    var guessText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("Welcome") }

    var gameWon by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        DecimalOutlinedTextField(
            onChange = {
                guessText = it
            }
        )
        Text(text = "Counter: ${gameViewModel.counter}")

        Button(
            enabled = guessText.isNotEmpty(),
            onClick = {
                gameViewModel.increaseCounter()

                try {
                    val myNum = guessText.toInt()
                    if (myNum == gameViewModel.generatedNum) {
                        resultText = context.getString(R.string.text_win)

                        gameWon = true

                    } else if (myNum < gameViewModel.generatedNum) {
                        resultText = "The number is higher!"
                    } else {
                        resultText = "The number is lower!"
                    }
                } catch (e: Exception) {
                    //Toast.makeText(context, "Error ${e.message}", Toast.LENGTH_LONG).show()
                    resultText = "Error ${e.message}"
                }
            }) {
            Text(text = stringResource(R.string.button_guess))
        }
        Button(onClick = {
            gameViewModel.generateNewNum()
            resultText = "Welcome"
            gameWon = false
        }) {
            Text(text = "Restart")
        }
        Text(
            text = resultText,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.Blue
        )

        if (gameWon) {
            // Show WIN dialog
            SimpleAlertDialog(
                onDismiss = {
                    gameWon = false
                },
                onConfirm = {
                    gameWon = false
                })
        }
    }
}


@Composable
fun SimpleAlertDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Congratulations") },
        text = { Text(text = "You have won!") },
        confirmButton = {
            TextButton(onClick = onConfirm)
            { Text(text = "OK") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss)
            { Text(text = "Cancel") }
        }
    )
}

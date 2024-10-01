package hu.bme.aut.filedemo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FileScreen(
    fileViewModel: FileViewModel = hiltViewModel()
) {
    var textToWriteToFile by rememberSaveable {
        mutableStateOf("")
    }

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.8f),
            label = {
                Text(text = "Content")
            },
            value = textToWriteToFile,
            onValueChange = {
                textToWriteToFile = it
            }
        )
        OutlinedButton(onClick = {
            fileViewModel.writeInternalFile(textToWriteToFile)
        }) {
            Text(text = "Write to internal file")
        }
        OutlinedButton(onClick = {
            fileViewModel.readInternalFile()
        }) {
            Text(text = "Read from internal file")
        }


        when (fileViewModel.fileScreenUiState) {
            is FileScreenUiState.Loading -> CircularProgressIndicator()
            is FileScreenUiState.WriteSuccess -> Text(text = "File write OK")
            is FileScreenUiState.ReadSuccess -> Text(text = "File content: ${
                (fileViewModel.fileScreenUiState as FileScreenUiState.ReadSuccess).fileText
            }")
            is FileScreenUiState.Error -> Text(text = "Error: ${
                (fileViewModel.fileScreenUiState as FileScreenUiState.Error).error
            }")
            FileScreenUiState.Init -> {}
        }
    }

}
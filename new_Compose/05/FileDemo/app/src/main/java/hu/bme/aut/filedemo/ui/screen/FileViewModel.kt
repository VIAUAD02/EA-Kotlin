package hu.bme.aut.filedemo.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.filedemo.repository.FileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface FileScreenUiState {
    object Init : FileScreenUiState
    object Loading : FileScreenUiState
    object WriteSuccess : FileScreenUiState
    data class ReadSuccess(val fileText: String?) : FileScreenUiState
    data class Error(val error: String?) : FileScreenUiState
}

@HiltViewModel
class FileViewModel @Inject constructor(
    private val fileRepository: FileRepository
) : ViewModel() {

    var fileScreenUiState: FileScreenUiState by mutableStateOf(FileScreenUiState.Init)

    fun writeInternalFile(data: String) {
        viewModelScope.launch {
            fileScreenUiState = FileScreenUiState.Loading
            delay(3000)
            fileScreenUiState = try {
                fileRepository.writeToInternalStorage(data)
                FileScreenUiState.WriteSuccess
            } catch (e: Exception) {
                FileScreenUiState.Error(e.message)
            }
        }
    }

    fun readInternalFile() {
        viewModelScope.launch {
            fileScreenUiState = FileScreenUiState.Loading
            delay(3000)
            fileScreenUiState = try {
                val result = fileRepository.readFromInternalStorage()
                FileScreenUiState.ReadSuccess(result)
            } catch (e: Exception) {
                FileScreenUiState.Error(e.message)
            }
        }
    }

}
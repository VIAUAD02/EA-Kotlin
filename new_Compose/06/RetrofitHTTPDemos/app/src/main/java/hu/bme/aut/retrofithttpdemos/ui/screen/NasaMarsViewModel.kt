package hu.bme.aut.retrofithttpdemos.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import hu.bme.aut.retrofithttpdemos.network.MarsApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val rowerPhotsResult: RowerPhotos) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class NasaMarsViewModel() : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)

    init {
        getRowerPhotos("2015-6-3")
    }

    fun getRowerPhotos(date: String) {
        marsUiState = MarsUiState.Loading
        viewModelScope.launch {
            marsUiState = try {
                val result = MarsApi.retrofitService.getRowerPhotos(
                    date,"DEMO_KEY"
                )
                MarsUiState.Success(result)
            } catch (e: IOException) {
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}
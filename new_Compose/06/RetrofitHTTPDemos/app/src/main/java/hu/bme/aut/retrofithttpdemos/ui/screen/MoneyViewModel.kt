package hu.bme.aut.retrofithttpdemos.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import hu.bme.aut.retrofithttpdemos.data.money.MoneyRates
import hu.bme.aut.retrofithttpdemos.network.MarsApi
import hu.bme.aut.retrofithttpdemos.network.MoneyApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MoneyUiState {
    data class Success(val moneyRates: MoneyRates) : MoneyUiState
    object Error : MoneyUiState
    object Loading : MoneyUiState
}

class MoneyViewModel() : ViewModel() {

    var moneyUiState: MoneyUiState by mutableStateOf(MoneyUiState.Loading)

    init {
        getRates("969c37b5a73f8cb2d12c081dcbdc35e6")
    }

    fun getRates(accessKey: String) {
        moneyUiState = MoneyUiState.Loading
        viewModelScope.launch {
            moneyUiState = try {
                val result = MoneyApi.retrofitService.getRates(
                    accessKey
                )
                MoneyUiState.Success(result)
            } catch (e: IOException) {
                MoneyUiState.Error
            } catch (e: HttpException) {
                MoneyUiState.Error
            }
        }
    }
}
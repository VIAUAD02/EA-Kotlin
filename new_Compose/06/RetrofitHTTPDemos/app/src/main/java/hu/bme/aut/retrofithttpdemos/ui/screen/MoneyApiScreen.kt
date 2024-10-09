package hu.bme.aut.retrofithttpdemos.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import hu.bme.aut.retrofithttpdemos.data.money.MoneyRates


@Composable
fun MoneyApiScreen(
    moneyViewModel: MoneyViewModel = viewModel()
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = {
            moneyViewModel.getRates("969c37b5a73f8cb2d12c081dcbdc35e6")
        }) {
            Text(text = "Refresh")
        }
        when (moneyViewModel.moneyUiState) {
            is MoneyUiState.Loading -> CircularProgressIndicator()
            is MoneyUiState.Success -> ResultScreen((moneyViewModel.moneyUiState as MoneyUiState.Success).moneyRates)
            is MoneyUiState.Error -> Text(text = "Error...")
        }
    }

}

@Composable
fun ResultScreen(moneyRates: MoneyRates) {
    Column() {
        Text(text = "Base: EUR")
        Text(text = "USD: ${moneyRates.rates?.uSD}")
        Text(text = "HUF: ${moneyRates.rates?.hUF}")

    }
}

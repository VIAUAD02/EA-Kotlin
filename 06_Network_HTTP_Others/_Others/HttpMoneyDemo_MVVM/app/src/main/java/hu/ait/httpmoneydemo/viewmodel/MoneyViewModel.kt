package hu.ait.httpmoneydemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import hu.ait.httpmoneydemo.repository.MoneyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoneyViewModel(application: Application) : AndroidViewModel(application) {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val repository: MoneyRepository = MoneyRepository()

    fun getRates(eurAmount: Double) = viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch {
            try {
                _response.value = "HUF value: ${repository.getRates().rates!!.HUF!! * eurAmount}"
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}
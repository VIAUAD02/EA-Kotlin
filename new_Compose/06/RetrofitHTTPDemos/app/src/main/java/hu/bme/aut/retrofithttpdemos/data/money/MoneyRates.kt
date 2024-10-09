package hu.bme.aut.retrofithttpdemos.data.money


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoneyRates(
    @SerialName("base")
    val base: String? = null,
    @SerialName("date")
    val date: String? = null,
    @SerialName("rates")
    val rates: Rates? = null,
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("timestamp")
    val timestamp: Int? = null
)
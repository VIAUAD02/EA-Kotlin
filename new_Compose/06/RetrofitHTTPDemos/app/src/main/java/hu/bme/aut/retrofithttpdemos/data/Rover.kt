package hu.bme.aut.retrofithttpdemos.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rover(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("landing_date")
    val landingDate: String? = null,
    @SerialName("launch_date")
    val launchDate: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("status")
    val status: String? = null
)
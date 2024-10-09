package hu.bme.aut.retrofithttpdemos.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Camera(
    @SerialName("full_name")
    val fullName: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("rover_id")
    val roverId: Int? = null
)
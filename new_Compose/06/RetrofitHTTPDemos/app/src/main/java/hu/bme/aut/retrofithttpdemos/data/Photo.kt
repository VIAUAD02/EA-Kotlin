package hu.bme.aut.retrofithttpdemos.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("camera")
    val camera: Camera? = null,
    @SerialName("earth_date")
    val earthDate: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("img_src")
    val imgSrc: String? = null,
    @SerialName("rover")
    val rover: Rover? = null,
    @SerialName("sol")
    val sol: Int? = null
)
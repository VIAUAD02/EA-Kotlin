package hu.bme.aut.retrofithttpdemos.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RowerPhotos(
    @SerialName("photos")
    val photos: List<Photo?>? = null
)
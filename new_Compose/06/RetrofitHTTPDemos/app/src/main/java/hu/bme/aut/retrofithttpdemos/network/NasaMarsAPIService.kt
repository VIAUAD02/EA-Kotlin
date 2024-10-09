package hu.bme.aut.retrofithttpdemos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


// https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=DEMO_KEY

private const val BASE_URL =
    "https://api.nasa.gov/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json{
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())
    )
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

interface MarsApiService {
    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getRowerPhotos(@Query("earth_date") earthDate: String,
                               @Query("api_key") apikey: String): RowerPhotos
}
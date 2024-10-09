package hu.bme.aut.retrofithttpdemos.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.bme.aut.retrofithttpdemos.data.RowerPhotos
import hu.bme.aut.retrofithttpdemos.data.money.MoneyRates
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// http://data.fixer.io/api/latest?access_key=969c37b5a73f8cb2d12c081dcbdc35e6

private const val BASE_URL =
    "http://data.fixer.io/"

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
object MoneyApi {
    val retrofitService: MoneyApiService by lazy {
        retrofit.create(MoneyApiService::class.java)
    }
}

interface MoneyApiService {
    @GET("api/latest")
    suspend fun getRates(@Query("access_key") access_key: String): MoneyRates
}
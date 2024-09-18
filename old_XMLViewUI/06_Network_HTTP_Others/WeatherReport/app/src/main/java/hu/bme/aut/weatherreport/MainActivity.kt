package hu.bme.aut.weatherreport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import hu.bme.aut.data.Base
import hu.bme.aut.network.WeatherAPI
import hu.bme.aut.weatherreport.databinding.ActivityMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var weatherAPI: WeatherAPI
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        weatherAPI = retrofit.create(WeatherAPI::class.java)

        binding.btnGet.setOnClickListener {
            queryWeather()
        }
    }

    private fun queryWeather() {
        val call = weatherAPI.getWeatherDetails(binding.etCity.text.toString(),
                "metric",
                "f3d694bc3e1d44c1ed5a97bd1120e8fe")

        call.enqueue(object: Callback<Base> {
            override fun onResponse(call: Call<Base>, response: Response<Base>) {
                if (response.body() == null) {


                    binding.tvData.text = JSONObject(response.errorBody()?.string()).getString("message")


                } else {

                    binding.tvData.text = """
                    ${response.body()?.name}
                    ${response.body()?.main?.temp}
                    ${response.body()?.weather?.get(0)?.description}
                """.trimIndent()

                    Glide.with(this@MainActivity)
                        .load(
                            ("https://openweathermap.org/img/w/" +
                                    response.body()?.weather?.get(0)?.icon
                                    + ".png")
                        )
                        .into(binding.ivWeather)
                }
            }

            override fun onFailure(call: Call<Base>, t: Throwable) {
                binding.tvData.text = t.message
            }
        })
    }
}
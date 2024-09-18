package hu.ait.httpmoneydemo.repository

import hu.ait.httpmoneydemo.data.MoneyResult
import hu.ait.httpmoneydemo.network.MoneyAPI
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MoneyRepository() {

    private var retrofit: Retrofit
    private var currencyAPI: MoneyAPI

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        currencyAPI = retrofit.create(MoneyAPI::class.java)
    }

    suspend fun getRates(): MoneyResult {
        return currencyAPI.getMoney("USD")
    }


}
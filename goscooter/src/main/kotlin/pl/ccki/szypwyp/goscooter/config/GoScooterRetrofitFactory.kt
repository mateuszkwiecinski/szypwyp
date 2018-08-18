package pl.ccki.szypwyp.goscooter.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GoScooterRetrofitFactory {

    fun create(apiUrl: String): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(apiUrl)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
}

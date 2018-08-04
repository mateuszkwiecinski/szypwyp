package pl.ccki.szypwyp.blinkee.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BlinkeeRetrofitFactory {
    private const val API_URL = "https://blinkee.city/"

    fun create(apiUrl: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(apiUrl)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
}


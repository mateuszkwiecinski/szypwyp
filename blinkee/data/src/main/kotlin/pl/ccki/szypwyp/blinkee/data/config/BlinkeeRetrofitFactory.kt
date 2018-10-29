package pl.ccki.szypwyp.blinkee.data.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BlinkeeRetrofitFactory {

    fun create(apiUrl: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(apiUrl)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
}

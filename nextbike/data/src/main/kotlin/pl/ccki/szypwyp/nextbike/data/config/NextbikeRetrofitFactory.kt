package pl.ccki.szypwyp.nextbike.data.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NextbikeRetrofitFactory {

    fun create(apiUrl: String): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(apiUrl)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
}

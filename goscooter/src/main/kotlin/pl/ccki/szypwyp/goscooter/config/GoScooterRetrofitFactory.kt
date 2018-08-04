package pl.ccki.szypwyp.goscooter.config

import retrofit2.Retrofit

object GoScooterRetrofitFactory {

    fun create(apiUrl: String): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(apiUrl)
        }.build()
}

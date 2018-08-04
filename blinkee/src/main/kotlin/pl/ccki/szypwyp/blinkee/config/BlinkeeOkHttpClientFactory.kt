package pl.ccki.szypwyp.blinkee.config

import okhttp3.OkHttpClient

object BlinkeeOkHttpClientFactory {
    fun create(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(BlinkeeRefererInterceptor())
        }.build()
}

package pl.ccki.szypwyp.blinkee

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
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

object BlinkeOkHttpClientFactory {
    fun create(): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(BlinkeeRefererInterceptor())
        }.build()
}

class BlinkeeRefererInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response? {
        chain ?: return null

        val request = chain
            .request()
            .newBuilder()
            .addHeader("Referer", "https://blinkee.city/")
            .build()

        return chain.proceed(request)
    }

}

package pl.ccki.szypwyp.blinkee.data.config

import okhttp3.Interceptor
import okhttp3.Response

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

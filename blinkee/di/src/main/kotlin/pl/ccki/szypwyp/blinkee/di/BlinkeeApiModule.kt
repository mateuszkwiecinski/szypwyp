package pl.ccki.szypwyp.blinkee.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.ccki.szypwyp.blinkee.data.config.BlinkeeEndpoints
import pl.ccki.szypwyp.blinkee.data.config.BlinkeeOkHttpClientFactory
import pl.ccki.szypwyp.blinkee.data.config.BlinkeeRetrofitFactory
import retrofit2.Retrofit

@Module
class BlinkeeApiModule {
    @Provides
    fun apiUrl(): String = "https://blinkee.city/"

    @Provides
    fun okHttpClient(): OkHttpClient = BlinkeeOkHttpClientFactory.create()

    @Provides
    fun retrofit(apiUrl: String, okHttpClient: OkHttpClient): Retrofit =
        BlinkeeRetrofitFactory.create(apiUrl, okHttpClient)

    @Provides
    fun blinkeeEndpoints(retrofit: Retrofit): BlinkeeEndpoints =
        retrofit.create(BlinkeeEndpoints::class.java)
}

package pl.ccki.szypwyp.blinkee.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pl.ccki.szypwyp.blinkee.config.BlinkeeOkHttpClientFactory
import pl.ccki.szypwyp.blinkee.BlinkeeRepository
import pl.ccki.szypwyp.blinkee.config.BlinkeeEndpoints
import pl.ccki.szypwyp.blinkee.config.BlinkeeRetrofitFactory
import retrofit2.Retrofit

@Module
class BlinkeeModule {

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

    @Provides
    fun blinkeeRepository(enpoints: BlinkeeEndpoints): BlinkeeRepository =
        BlinkeeRepository(enpoints)
}
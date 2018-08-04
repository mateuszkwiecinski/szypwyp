package pl.ccki.szypwyp.blinkee

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class BlinkeeModule {

    @Provides
    fun apiUrl(): String = "https://blinkee.city/"

    @Provides
    fun okHttpClient(): OkHttpClient = BlinkeOkHttpClientFactory.create()

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
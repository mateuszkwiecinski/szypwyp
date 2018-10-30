package pl.ccki.szypwyp.traficar.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.traficar.data.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.data.config.TraficarRetrofitFactory
import retrofit2.Retrofit

@Module
class TraficarApiModule {

    @Provides
    fun apiUrl(): String = "https://api.traficar.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        TraficarRetrofitFactory.create(apiUrl)

    @Provides
    fun vozillaEndpoints(retrofit: Retrofit): TraficarEndpoints =
        retrofit.create(TraficarEndpoints::class.java)
}

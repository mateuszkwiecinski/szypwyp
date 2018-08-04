package pl.ccki.szypwyp.traficar.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.traficar.TraficarRepository
import pl.ccki.szypwyp.traficar.config.TraficarEndpoints
import pl.ccki.szypwyp.traficar.config.TraficarRetrofitFactory
import retrofit2.Retrofit

@Module
class TraficarModule {

    @Provides
    fun apiUrl(): String = "https://api.traficar.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        TraficarRetrofitFactory.create(apiUrl)

    @Provides
    fun traficarEndpoints(retrofit: Retrofit): TraficarEndpoints =
        retrofit.create(TraficarEndpoints::class.java)

    @Provides
    fun traficarRepository(enpoints: TraficarEndpoints): TraficarRepository =
        TraficarRepository(enpoints)
}

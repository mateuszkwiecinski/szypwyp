package pl.ccki.szypwyp.goscooter.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.goscooter.GoScooterRepository
import pl.ccki.szypwyp.goscooter.config.GoScooterEndpoints
import pl.ccki.szypwyp.goscooter.config.GoScooterRetrofitFactory
import retrofit2.Retrofit

@Module
class GoScooterModule {

    @Provides
    fun apiUrl(): String = "https://client.goscooter.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        GoScooterRetrofitFactory.create(apiUrl)

    @Provides
    fun goScooterEndpoints(retrofit: Retrofit): GoScooterEndpoints =
        retrofit.create(GoScooterEndpoints::class.java)

    @Provides
    fun goScooterRepository(enpoints: GoScooterEndpoints, apiUrl: String): GoScooterRepository =
        GoScooterRepository(enpoints, apiUrl)
}

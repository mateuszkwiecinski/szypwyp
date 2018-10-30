package pl.ccki.szypwyp.goscooter.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.goscooter.data.config.GoScooterRetrofitFactory
import pl.ccki.szypwyp.goscooter.data.config.GoScooterEndpoints
import retrofit2.Retrofit

@Module
class GoScooterApiModule {

    @Provides
    fun apiUrl(): String = "https://client.goscooter.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        GoScooterRetrofitFactory.create(apiUrl)

    @Provides
    fun goScooterEndpoints(retrofit: Retrofit): GoScooterEndpoints =
        retrofit.create(GoScooterEndpoints::class.java)
}

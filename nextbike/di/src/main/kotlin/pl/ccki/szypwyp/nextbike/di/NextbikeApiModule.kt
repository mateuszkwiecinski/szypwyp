package pl.ccki.szypwyp.nextbike.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.nextbike.data.config.NextbikeEndpoints
import pl.ccki.szypwyp.nextbike.data.config.NextbikeRetrofitFactory
import retrofit2.Retrofit

@Module
class NextbikeApiModule {
    @Provides
    fun apiUrl(): String = "https://api.nextbike.net/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        NextbikeRetrofitFactory.create(apiUrl)

    @Provides
    fun nextbikeEndpoints(retrofit: Retrofit): NextbikeEndpoints =
        retrofit.create(NextbikeEndpoints::class.java)
}

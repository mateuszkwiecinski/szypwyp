package pl.ccki.szypwyp.vozilla.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.vozilla.data.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.data.config.VozillaRetrofitFactory
import retrofit2.Retrofit

@Module
class VozillaApiModule {

    @Provides
    fun apiUrl(): String = "https://api-client-portal.vozilla.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        VozillaRetrofitFactory.create(apiUrl)

    @Provides
    fun vozillaEndpoints(retrofit: Retrofit): VozillaEndpoints =
        retrofit.create(VozillaEndpoints::class.java)
}

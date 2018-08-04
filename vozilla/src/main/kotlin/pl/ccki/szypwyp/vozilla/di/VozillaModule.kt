package pl.ccki.szypwyp.vozilla.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.vozilla.VozillaRepository
import pl.ccki.szypwyp.vozilla.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.config.VozillaRetrofitFactory
import retrofit2.Retrofit

@Module
class VozillaModule {

    @Provides
    fun apiUrl(): String = "https://api-client-portal.vozilla.pl/"

    @Provides
    fun retrofit(apiUrl: String): Retrofit =
        VozillaRetrofitFactory.create(apiUrl)

    @Provides
    fun vozillaEndpoints(retrofit: Retrofit): VozillaEndpoints =
        retrofit.create(VozillaEndpoints::class.java)

    @Provides
    fun vozillaRepository(enpoints: VozillaEndpoints): VozillaRepository =
        VozillaRepository(enpoints)
}
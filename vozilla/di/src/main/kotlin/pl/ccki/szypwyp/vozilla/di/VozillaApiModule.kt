package pl.ccki.szypwyp.vozilla.di

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.vozilla.data.config.VozillaEndpoints
import pl.ccki.szypwyp.vozilla.data.config.VozillaRetrofitFactory
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
class VozillaApiModule {

    @Provides
    @ApiUrl
    fun apiUrl(): String = "https://api-client-portal.vozilla.pl/"

    @Provides
    fun retrofit(@ApiUrl apiUrl: String): Retrofit =
        VozillaRetrofitFactory.create(apiUrl)

    @Provides
    fun vozillaEndpoints(retrofit: Retrofit): VozillaEndpoints =
        retrofit.create(VozillaEndpoints::class.java)
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApiUrl

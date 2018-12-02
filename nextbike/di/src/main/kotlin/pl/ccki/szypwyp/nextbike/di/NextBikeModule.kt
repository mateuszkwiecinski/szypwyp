package pl.ccki.szypwyp.nextbike.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.nextbike.data.RemoteNextbikeRepository
import pl.ccki.szypwyp.nextbike.domain.NextbikePlugin
import pl.ccki.szypwyp.nextbike.domain.NextbikeRepository
import pl.ccki.szypwyp.nextbike.presentation.NextbikeMapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider

@Module(includes = [NextbikeApiModule::class])
abstract class NextBikeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Nextbike
        fun id() = PluginId("780a0816-f227-nextbike-b48f-709380fa07bd")
    }

    @Binds
    abstract fun repository(impl: RemoteNextbikeRepository): NextbikeRepository

    @Binds
    @Nextbike
    abstract fun plugin(impl: NextbikeMapViewsProvider): MapViewsProvider

    @Binds
    @Nextbike
    abstract fun mapViews(impl: NextbikePlugin): ExternalPlugin
}

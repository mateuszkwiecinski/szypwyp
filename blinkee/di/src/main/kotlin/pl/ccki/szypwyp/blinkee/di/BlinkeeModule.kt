package pl.ccki.szypwyp.blinkee.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.blinkee.data.RemoteBlinkeeRepository
import pl.ccki.szypwyp.blinkee.domain.BlinkeePlugin
import pl.ccki.szypwyp.blinkee.domain.BlinkeeRepository
import pl.ccki.szypwyp.blinkee.presentation.BlinkeeMapViewsProvider
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider

@Module(includes = [BlinkeeApiModule::class])
abstract class BlinkeeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Blinkee
        fun id() = PluginId("780a0816-f227-blinkee-b48f-709380fa07bd")
    }

    @Binds
    abstract fun repository(impl: RemoteBlinkeeRepository): BlinkeeRepository

    @Binds
    @Blinkee
    abstract fun plugin(impl: BlinkeeMapViewsProvider): MapViewsProvider

    @Binds
    @Blinkee
    abstract fun mapViews(impl: BlinkeePlugin): ExternalPlugin
}

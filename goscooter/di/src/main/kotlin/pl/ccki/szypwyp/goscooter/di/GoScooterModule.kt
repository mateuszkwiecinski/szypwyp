package pl.ccki.szypwyp.goscooter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.goscooter.data.RemoteGoScooterRepository
import pl.ccki.szypwyp.goscooter.domain.GoScooterPlugin
import pl.ccki.szypwyp.goscooter.domain.GoScooterRepository
import pl.ccki.szypwyp.goscooter.presentation.GoScooterMapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider

@Module(includes = [GoScooterApiModule::class])
abstract class GoScooterModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @GoScooter
        fun id() = PluginId("d7730c69-2a8f-goscooter-9ade-f82351404ee5")
    }

    @Binds
    abstract fun repository(impl: RemoteGoScooterRepository): GoScooterRepository

    @Binds
    @GoScooter
    abstract fun plugin(impl: GoScooterMapViewsProvider): MapViewsProvider

    @Binds
    @GoScooter
    abstract fun mapViews(impl: GoScooterPlugin): ExternalPlugin
}

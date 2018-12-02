package pl.ccki.szypwyp.vozilla.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.vozilla.data.RemoteVozillaRepository
import pl.ccki.szypwyp.vozilla.domain.VozillaPlugin
import pl.ccki.szypwyp.vozilla.domain.VozillaRepository
import pl.ccki.szypwyp.vozilla.presentation.VozillaMapViewsProvider
import pl.ccki.szypwyp.vozilla.presentation.filters.VozillaFilterViewsProvider

@Module(includes = [VozillaApiModule::class])
abstract class VozillaModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Vozilla
        fun id() = PluginId("3ff8180f-73b3-vozilla-936f-0da81b967c7e")
    }

    @Binds
    abstract fun repository(impl: RemoteVozillaRepository): VozillaRepository

    @Binds
    @Vozilla
    abstract fun plugin(impl: VozillaPlugin): ExternalPlugin

    @Binds
    @Vozilla
    abstract fun mapViews(impl: VozillaMapViewsProvider): MapViewsProvider

    @Binds
    @Vozilla
    abstract fun filterViews(impl: VozillaFilterViewsProvider): FilterViewsProvider
}

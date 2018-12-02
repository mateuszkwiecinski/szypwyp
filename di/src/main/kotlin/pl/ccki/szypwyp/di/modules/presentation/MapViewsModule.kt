package pl.ccki.szypwyp.di.modules.presentation

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.blinkee.di.Blinkee
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.goscooter.di.GoScooter
import pl.ccki.szypwyp.nextbike.di.Nextbike
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.traficar.di.Traficar
import pl.ccki.szypwyp.vozilla.di.Vozilla

@Module
class MapViewsModule {
    @Provides
    @IntoSet
    fun vozilla(@Vozilla id: PluginId, @Vozilla provider: MapViewsProvider): Pair<PluginId, MapViewsProvider> =
        id to provider

    @Provides
    @IntoSet
    fun blinkee(@Blinkee id: PluginId, @Blinkee provider: MapViewsProvider): Pair<PluginId, MapViewsProvider> =
        id to provider

    @Provides
    @IntoSet
    fun traficar(@Traficar id: PluginId, @Traficar provider: MapViewsProvider): Pair<PluginId, MapViewsProvider> =
        id to provider

    @Provides
    @IntoSet
    fun goScooter(@GoScooter id: PluginId, @GoScooter provider: MapViewsProvider): Pair<PluginId, MapViewsProvider> =
        id to provider

    @Provides
    @IntoSet
    fun nextbike(@Nextbike id: PluginId, @Nextbike provider: MapViewsProvider): Pair<PluginId, MapViewsProvider> =
        id to provider
}

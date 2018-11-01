package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.blinkee.domain.BlinkeeId
import pl.ccki.szypwyp.blinkee.domain.BlinkeePlugin
import pl.ccki.szypwyp.blinkee.presentation.BlinkeeMapViewsProvider
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.goscooter.domain.GoScooterId
import pl.ccki.szypwyp.goscooter.domain.GoScooterPlugin
import pl.ccki.szypwyp.goscooter.presentation.GoScooterMapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.traficar.domain.TraficarId
import pl.ccki.szypwyp.traficar.domain.TraficarPlugin
import pl.ccki.szypwyp.traficar.presentation.TraficarMapViewsProvider
import pl.ccki.szypwyp.vozilla.domain.VozillaId
import pl.ccki.szypwyp.vozilla.domain.VozillaPlugin
import pl.ccki.szypwyp.vozilla.presentation.VozillaMapViewsProvider

@Module(includes = [DomainPlugins::class, PresentationPlugins::class])
class PluginsModule {
    @Provides
    fun plugins(
        set: Set<@JvmSuppressWildcards Pair<PluginId, ExternalPlugin>>
    ): InjectableMap<PluginId, ExternalPlugin> =
        set.toMap()

    @Provides
    fun mapViews(
        set: Set<@JvmSuppressWildcards Pair<PluginId, MapViewsProvider<MarkerModel>>>
    ): InjectableMap<PluginId, MapViewsProvider<MarkerModel>> =
        set.toMap()
}

@Module
class DomainPlugins {
    @Provides
    @IntoSet
    fun blinkee(id: BlinkeeId, plugin: BlinkeePlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @Provides
    @IntoSet
    fun traficar(id: TraficarId, plugin: TraficarPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @Provides
    @IntoSet
    fun vozilla(id: VozillaId, plugin: VozillaPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @Provides
    @IntoSet
    fun goScooter(id: GoScooterId, plugin: GoScooterPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin
}

@Module
@Suppress("unchecked_cast")
class PresentationPlugins {

    @Provides
    @IntoSet
    fun vozilla(id: VozillaId, provider: VozillaMapViewsProvider): Pair<PluginId, MapViewsProvider<MarkerModel>> =
        id to provider as MapViewsProvider<MarkerModel>

    @Provides
    @IntoSet
    fun blinkee(id: BlinkeeId, provider: BlinkeeMapViewsProvider): Pair<PluginId, MapViewsProvider<MarkerModel>> =
        id to provider as MapViewsProvider<MarkerModel>


    @Provides
    @IntoSet
    fun traficar(id: TraficarId, provider: TraficarMapViewsProvider): Pair<PluginId, MapViewsProvider<MarkerModel>> =
        id to provider as MapViewsProvider<MarkerModel>


    @Provides
    @IntoSet
    fun goScooter(id: GoScooterId, provider: GoScooterMapViewsProvider): Pair<PluginId, MapViewsProvider<MarkerModel>> =
        id to provider as MapViewsProvider<MarkerModel>
}

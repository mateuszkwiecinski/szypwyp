package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.di.DebugObject
import pl.ccki.szypwyp.di.ProductionObject
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Provider

@Module(includes = [DomainPlugins::class, PresentationPlugins::class])
class PluginsModule {

    @Provides
    fun plugins(
        isDebug: IsDebug,
        @DebugObject debug: Provider<Set<@JvmSuppressWildcards Pair<PluginId, ExternalPlugin>>>,
        @ProductionObject production: Provider<Set<@JvmSuppressWildcards Pair<PluginId, ExternalPlugin>>>
    ): InjectableMap<PluginId, ExternalPlugin> =
        if (isDebug.value) {
            debug.get()
        } else {
            production.get()
        }.toMap()

    @Provides
    fun mapViews(
        set: Set<@JvmSuppressWildcards Pair<PluginId, MapViewsProvider<MarkerModel>>>
    ): InjectableMap<PluginId, MapViewsProvider<MarkerModel>> =
        set.toMap()
}

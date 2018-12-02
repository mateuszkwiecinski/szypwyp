package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.BuildConfig
import pl.ccki.szypwyp.di.DebugObject
import pl.ccki.szypwyp.di.ProductionObject
import pl.ccki.szypwyp.di.modules.presentation.FilterViewsModule
import pl.ccki.szypwyp.di.modules.presentation.MapViewsModule
import pl.ccki.szypwyp.domain.base.InjectableMap
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Provider

@Module(includes = [DomainPlugins::class, PresentationPlugins::class])
class PluginsModule {

    @Provides
    fun plugins(
        @DebugObject debug: Provider<Set<@JvmSuppressWildcards Pair<PluginId, ExternalPlugin>>>,
        @ProductionObject production: Provider<Set<@JvmSuppressWildcards Pair<PluginId, ExternalPlugin>>>
    ): InjectableMap<PluginId, ExternalPlugin> =
        if (BuildConfig.DEBUG) {
            production.get()
        } else {
            production.get()
        }.toMap()

    @Provides
    fun mapViews(
        set: Set<@JvmSuppressWildcards Pair<PluginId, MapViewsProvider>>
    ): InjectableMap<PluginId, MapViewsProvider> =
        set.toMap()

    @Provides
    fun filterViews(
        set: Set<@JvmSuppressWildcards Pair<PluginId, FilterViewsProvider>>
    ): InjectableMap<PluginId, FilterViewsProvider> =
        set.toMap()
}

@Module(includes = [MapViewsModule::class, FilterViewsModule::class])
abstract class PresentationPlugins


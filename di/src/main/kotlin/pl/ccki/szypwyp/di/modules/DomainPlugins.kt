package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.blinkee.di.Blinkee
import pl.ccki.szypwyp.di.ProductionObject
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.goscooter.di.GoScooter
import pl.ccki.szypwyp.nextbike.di.Nextbike
import pl.ccki.szypwyp.traficar.di.Traficar
import pl.ccki.szypwyp.vozilla.di.Vozilla

@Module
class DomainPlugins {
    @ProductionObject
    @Provides
    @IntoSet
    fun blinkee(@Blinkee id: PluginId, @Blinkee plugin: ExternalPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun traficar(@Traficar id: PluginId, @Traficar plugin: ExternalPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun vozilla(@Vozilla id: PluginId, @Vozilla plugin: ExternalPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun goScooter(@GoScooter id: PluginId, @GoScooter plugin: ExternalPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun nextbike(@Nextbike id: PluginId, @Nextbike plugin: ExternalPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin
}

package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.blinkee.domain.BlinkeeId
import pl.ccki.szypwyp.blinkee.domain.BlinkeePlugin
import pl.ccki.szypwyp.di.ProductionObject
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.goscooter.domain.GoScooterId
import pl.ccki.szypwyp.goscooter.domain.GoScooterPlugin
import pl.ccki.szypwyp.nextbike.domain.NextbikeId
import pl.ccki.szypwyp.nextbike.domain.NextbikePlugin
import pl.ccki.szypwyp.traficar.domain.TraficarId
import pl.ccki.szypwyp.traficar.domain.TraficarPlugin
import pl.ccki.szypwyp.vozilla.domain.VozillaId
import pl.ccki.szypwyp.vozilla.domain.VozillaPlugin

@Module
class DomainPlugins {
    @ProductionObject
    @Provides
    @IntoSet
    fun blinkee(id: BlinkeeId, plugin: BlinkeePlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun traficar(id: TraficarId, plugin: TraficarPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun vozilla(id: VozillaId, plugin: VozillaPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun goScooter(id: GoScooterId, plugin: GoScooterPlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin

    @ProductionObject
    @Provides
    @IntoSet
    fun nextbike(id: NextbikeId, plugin: NextbikePlugin): Pair<PluginId, ExternalPlugin> =
        id to plugin
}

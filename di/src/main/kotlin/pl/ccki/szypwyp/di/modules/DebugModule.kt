package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.di.DebugObject
import pl.ccki.szypwyp.di.DelayingPlugin
import pl.ccki.szypwyp.di.ThrowingPlugin
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin

@Module
class DebugModule {

    @DebugObject
    @Provides
    @IntoSet
    fun delaying(): Pair<PluginId, ExternalPlugin> = DelayingPlugin.id to DelayingPlugin

//    @DebugObject
//    @Provides
//    @IntoSet
//    fun throwing(): Pair<PluginId, ExternalPlugin> = ThrowingPlugin.id to ThrowingPlugin
}

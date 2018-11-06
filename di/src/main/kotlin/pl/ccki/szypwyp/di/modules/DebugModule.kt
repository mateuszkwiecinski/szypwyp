package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.BuildConfig
import pl.ccki.szypwyp.di.DebugObject
import pl.ccki.szypwyp.di.MockPlugin
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin

@Module
class DebugModule {

    @Provides
    fun isDebug(): IsDebug = IsDebug(BuildConfig.DEBUG)

    @DebugObject
    @Provides
    @IntoSet
    fun delaying(): Pair<PluginId, ExternalPlugin> = MockPlugin.id to MockPlugin
}

data class IsDebug(val value : Boolean)

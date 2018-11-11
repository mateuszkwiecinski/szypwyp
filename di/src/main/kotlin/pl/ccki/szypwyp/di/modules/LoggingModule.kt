package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.BuildConfig
import pl.ccki.szypwyp.di.DebugObject
import pl.ccki.szypwyp.di.ProductionObject
import pl.ccki.szypwyp.platform.implementations.remoteconfig.CrashlyticsTree
import timber.log.LogcatTree
import timber.log.Tree

@Module
class LoggingModule {

    @Provides
    fun logger(
        @DebugObject debug: Set<@JvmSuppressWildcards Tree>,
        @ProductionObject production: Set<@JvmSuppressWildcards Tree>
    ): Set<@JvmSuppressWildcards Tree> = if (BuildConfig.DEBUG) {
        debug
    } else {
        production
    }

    @Provides
    @DebugObject
    @IntoSet
    fun logcat(): Tree = LogcatTree()

    @Provides
    @ProductionObject
    @IntoSet
    fun crashlytics(): Tree = CrashlyticsTree()
}

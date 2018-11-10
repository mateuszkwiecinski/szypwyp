package pl.ccki.szypwyp.platform.internal.di

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.domain.providers.RemoteConfigProvider
import pl.ccki.szypwyp.platform.implementations.remoteconfig.FirebaseRemoteConfigRxWrapper

@Module
abstract class RemoteConfigModule {
    @Binds
    abstract fun configProvider(impl: FirebaseRemoteConfigRxWrapper): RemoteConfigProvider
}

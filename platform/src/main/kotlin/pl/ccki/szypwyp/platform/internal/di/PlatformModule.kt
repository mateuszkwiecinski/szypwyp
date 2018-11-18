package pl.ccki.szypwyp.platform.internal.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.providers.CurrentConfigProvider
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import pl.ccki.szypwyp.domain.services.AppOpeningService
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import pl.ccki.szypwyp.platform.implementations.appintegrations.AndroidAppOpeningService
import pl.ccki.szypwyp.platform.implementations.appintegrations.AndroidAppsCheckingService
import pl.ccki.szypwyp.platform.implementations.location.AndroidLocationProvider
import pl.ccki.szypwyp.platform.implementations.AndroidPermissionChecker
import pl.ccki.szypwyp.platform.implementations.remoteconfig.AndroidCurrentConfigProvider
import pl.ccki.szypwyp.platform.implementations.sharedprefs.SharedPrefsSearchConfigRepository

@Module(includes = [LocationModule::class, RemoteConfigModule::class])
abstract class PlatformModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun servicesConfiguration(): FiltersRepository = object : FiltersRepository {
            override var selected: Iterable<PluginId>? = null
        }
    }

    @Binds
    abstract fun searchConfig(impl: SharedPrefsSearchConfigRepository): SearchConfigRepository

    @Binds
    abstract fun permissionChecker(provider: AndroidPermissionChecker): PermissionChecker

    @Binds
    abstract fun openingService(provider: AndroidAppOpeningService): AppOpeningService

    @Binds
    abstract fun checkingService(provider: AndroidAppsCheckingService): AppsCheckingService

    @Binds
    abstract fun location(provider: AndroidLocationProvider): LocationProvider

    @Binds
    abstract fun currentConfig(provider: AndroidCurrentConfigProvider): CurrentConfigProvider
}

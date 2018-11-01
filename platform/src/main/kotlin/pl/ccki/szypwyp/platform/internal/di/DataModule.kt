package pl.ccki.szypwyp.platform.internal.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.domain.services.AppOpeningService
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import pl.ccki.szypwyp.platform.implementations.AndroidAppOpeningService
import pl.ccki.szypwyp.platform.implementations.AndroidAppsCheckingService
import pl.ccki.szypwyp.platform.implementations.AndroidLocationProvider
import pl.ccki.szypwyp.platform.implementations.AndroidPermissionChecker

@Module(includes = [LocationModule::class])
abstract class DataModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun servicesConfiguration(): ServicesConfigurationRepository = object : ServicesConfigurationRepository {
            override var selected: Iterable<PluginId>? = null
        }

        @Provides
        @JvmStatic
        fun searchConfig(): SearchConfigRepository = object : SearchConfigRepository {

            override var target: LatLng? = null
        }
    }

    @Binds
    abstract fun locationProvider(provider: AndroidLocationProvider): LocationProvider

    @Binds
    abstract fun permissionChecker(provider: AndroidPermissionChecker): PermissionChecker

    @Binds
    abstract fun openingService(provider: AndroidAppOpeningService): AppOpeningService

    @Binds
    abstract fun checkingService(provider: AndroidAppsCheckingService): AppsCheckingService
}

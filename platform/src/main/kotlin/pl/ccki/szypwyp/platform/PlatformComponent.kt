package pl.ccki.szypwyp.platform

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pl.ccki.szypwyp.domain.providers.CurrentConfigProvider
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.providers.RemoteConfigProvider
import pl.ccki.szypwyp.domain.repositories.PermissionChecker
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.FiltersRepository
import pl.ccki.szypwyp.domain.services.AppOpeningService
import pl.ccki.szypwyp.domain.services.AppsCheckingService
import pl.ccki.szypwyp.platform.internal.di.PlatformModule
import javax.inject.Scope

@PlatformSingleton
@Component(modules = [PlatformModule::class])
interface PlatformComponent {
    fun configuration(): FiltersRepository
    fun searchConfiguration(): SearchConfigRepository
    fun locationProvider(): LocationProvider
    fun permissionChecker(): PermissionChecker
    fun appOpening(): AppOpeningService
    fun appChecking(): AppsCheckingService
    fun remoteConfig(): RemoteConfigProvider
    fun appConfig() : CurrentConfigProvider

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun context(context: Context): Builder

        @BindsInstance
        abstract fun isDebug(context: Boolean): Builder

        abstract fun build(): PlatformComponent
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PlatformSingleton

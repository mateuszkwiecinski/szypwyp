package pl.ccki.szypwyp.platform

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.domain.repositories.SearchConfigRepository
import pl.ccki.szypwyp.domain.repositories.ServicesConfigurationRepository
import pl.ccki.szypwyp.platform.internal.di.DataModule

@Component(modules = [DataModule::class])
interface PlatformComponent {
    fun configuration(): ServicesConfigurationRepository
    fun searchConfiguration(): SearchConfigRepository
    fun locationProvider(): LocationProvider

    @Component.Builder
    abstract class Builder {
        @BindsInstance
        abstract fun context(context: Context): Builder

        abstract fun build(): PlatformComponent
    }
}

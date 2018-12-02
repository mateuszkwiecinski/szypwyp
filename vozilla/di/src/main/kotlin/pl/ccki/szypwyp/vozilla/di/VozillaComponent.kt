package pl.ccki.szypwyp.vozilla.di

import dagger.Component
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.FilterViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Qualifier

@Component(modules = [VozillaModule::class])
interface VozillaComponent {

    @Vozilla
    fun id(): PluginId

    @Vozilla
    fun plugin(): ExternalPlugin

    @Vozilla
    fun map(): MapViewsProvider

    @Vozilla
    fun filter(): FilterViewsProvider
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Vozilla

package pl.ccki.szypwyp.goscooter.di

import dagger.Component
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Qualifier

@Component(modules = [GoScooterModule::class])
interface GoScooterComponent {

    @GoScooter
    fun id(): PluginId

    @GoScooter
    fun plugin(): ExternalPlugin

    @GoScooter
    fun view(): MapViewsProvider
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GoScooter

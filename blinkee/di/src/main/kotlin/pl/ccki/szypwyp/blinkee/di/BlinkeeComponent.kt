package pl.ccki.szypwyp.blinkee.di

import dagger.Component
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Qualifier

@Component(modules = [BlinkeeModule::class])
interface BlinkeeComponent {

    @Blinkee
    fun id(): PluginId

    @Blinkee
    fun plugin(): ExternalPlugin

    @Blinkee
    fun view(): MapViewsProvider
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Blinkee

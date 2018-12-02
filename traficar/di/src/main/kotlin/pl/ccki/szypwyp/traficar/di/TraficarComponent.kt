package pl.ccki.szypwyp.traficar.di

import dagger.Component
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Qualifier

@Component(modules = [TraficarModule::class])
interface TraficarComponent {

    @Traficar
    fun id(): PluginId

    @Traficar
    fun plugin(): ExternalPlugin

    @Traficar
    fun view(): MapViewsProvider
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Traficar

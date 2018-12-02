package pl.ccki.szypwyp.nextbike.di

import dagger.Component
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.nextbike.domain.NextbikePlugin
import pl.ccki.szypwyp.nextbike.presentation.NextbikeMapViewsProvider
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import javax.inject.Qualifier

@Component(modules = [NextBikeModule::class])
interface NextbikeComponent {
    @Nextbike
    fun id(): PluginId

    @Nextbike
    fun plugin(): ExternalPlugin

    @Nextbike
    fun view(): MapViewsProvider
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Nextbike

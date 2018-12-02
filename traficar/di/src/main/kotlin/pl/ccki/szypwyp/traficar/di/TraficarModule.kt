package pl.ccki.szypwyp.traficar.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.presentation.interfaces.MapViewsProvider
import pl.ccki.szypwyp.traficar.data.RemoteTraficarRepository
import pl.ccki.szypwyp.traficar.domain.TraficarPlugin
import pl.ccki.szypwyp.traficar.domain.TraficarRepository
import pl.ccki.szypwyp.traficar.presentation.TraficarMapViewsProvider

@Module(includes = [TraficarApiModule::class])
abstract class TraficarModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @Traficar
        fun id() = PluginId("a21c0d56-13eb-traficar-ae54-25d3aca4354c")
    }

    @Binds
    abstract fun repository(impl: RemoteTraficarRepository): TraficarRepository

    @Binds
    @Traficar
    abstract fun plugin(impl: TraficarMapViewsProvider): MapViewsProvider

    @Binds
    @Traficar
    abstract fun mapViews(impl: TraficarPlugin): ExternalPlugin
}

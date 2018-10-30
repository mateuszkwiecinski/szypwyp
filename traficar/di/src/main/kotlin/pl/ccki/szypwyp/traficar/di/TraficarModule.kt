package pl.ccki.szypwyp.traficar.di

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.traficar.data.RemoteTraficarRepository
import pl.ccki.szypwyp.traficar.domain.IconProvider
import pl.ccki.szypwyp.traficar.domain.TraficarRepository
import pl.ccki.szypwyp.traficar.presentation.TraficarIconProvider

@Module(includes = [TraficarApiModule::class])
abstract class TraficarModule {
    @Binds
    abstract fun repository(impl: RemoteTraficarRepository): TraficarRepository

    @Binds
    abstract fun iconProvider(impl: TraficarIconProvider): IconProvider
}

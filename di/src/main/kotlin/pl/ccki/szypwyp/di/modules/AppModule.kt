package pl.ccki.szypwyp.di.modules

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.domain.providers.LocationProvider
import pl.ccki.szypwyp.platform.internal.AndroidLocationProvider
import pl.ccki.szypwyp.presentation.base.AndroidSchedulersProvider
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class, PersistencesModule::class])
abstract class AppModule {

    @Binds
    abstract fun shedulersProvider(android: AndroidSchedulersProvider): SchedulersProvider

    @Singleton
    @Binds
    abstract fun androidLocation(android: AndroidLocationProvider): LocationProvider
}

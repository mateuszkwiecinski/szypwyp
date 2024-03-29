package pl.ccki.szypwyp.di.modules

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.domain.base.SchedulersProvider
import pl.ccki.szypwyp.presentation.base.AndroidSchedulersProvider

@Module(includes = [PersistencesModule::class, LoggingModule::class])
abstract class AppModule {

    @Binds
    abstract fun schedulersProvider(android: AndroidSchedulersProvider): SchedulersProvider
}

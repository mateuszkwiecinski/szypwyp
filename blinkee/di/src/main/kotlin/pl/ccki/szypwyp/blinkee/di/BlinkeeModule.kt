package pl.ccki.szypwyp.blinkee.di

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.blinkee.data.RemoteBlinkeeRepository
import pl.ccki.szypwyp.blinkee.domain.BlinkeeRepository
import pl.ccki.szypwyp.blinkee.domain.IconProvider
import pl.ccki.szypwyp.blinkee.presentation.BlinkeeIconProvider

@Module(includes = [BlinkeeApiModule::class])
abstract class BlinkeeModule {

    @Binds
    abstract fun repository(impl: RemoteBlinkeeRepository): BlinkeeRepository

    @Binds
    abstract fun iconProvider(impl: BlinkeeIconProvider): IconProvider
}

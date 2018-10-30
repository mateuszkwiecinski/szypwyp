package pl.ccki.szypwyp.goscooter.di

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.goscooter.data.RemoteGoScooterRepository
import pl.ccki.szypwyp.goscooter.domain.GoScooterRepository
import pl.ccki.szypwyp.goscooter.domain.IconProvider
import pl.ccki.szypwyp.goscooter.presentation.GoScooterIconProvider

@Module(includes = [GoScooterApiModule::class])
abstract class GoScooterModule {

    @Binds
    abstract fun repository(impl: RemoteGoScooterRepository): GoScooterRepository

    @Binds
    abstract fun iconProvider(impl: GoScooterIconProvider): IconProvider
}

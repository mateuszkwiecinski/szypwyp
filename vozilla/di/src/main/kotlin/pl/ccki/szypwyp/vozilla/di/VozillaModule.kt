package pl.ccki.szypwyp.vozilla.di

import dagger.Binds
import dagger.Module
import pl.ccki.szypwyp.vozilla.data.RemoteVozillaRepository
import pl.ccki.szypwyp.vozilla.domain.IconProvider
import pl.ccki.szypwyp.vozilla.domain.VozillaRepository
import pl.ccki.szypwyp.vozilla.presentation.VozillaIconProvider

@Module(includes = [VozillaApiModule::class])
abstract class VozillaModule {
    @Binds
    abstract fun repository(impl: RemoteVozillaRepository): VozillaRepository

    @Binds
    abstract fun iconProvider(impl: VozillaIconProvider): IconProvider
}

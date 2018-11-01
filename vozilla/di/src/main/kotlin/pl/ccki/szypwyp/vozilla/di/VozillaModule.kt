package pl.ccki.szypwyp.vozilla.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.vozilla.data.RemoteVozillaRepository
import pl.ccki.szypwyp.vozilla.domain.VozillaId
import pl.ccki.szypwyp.vozilla.domain.VozillaRepository

@Module(includes = [VozillaApiModule::class])
abstract class VozillaModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun id() = VozillaId
    }

    @Binds
    abstract fun repository(impl: RemoteVozillaRepository): VozillaRepository
}

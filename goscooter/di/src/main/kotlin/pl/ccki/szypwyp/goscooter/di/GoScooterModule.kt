package pl.ccki.szypwyp.goscooter.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.goscooter.data.RemoteGoScooterRepository
import pl.ccki.szypwyp.goscooter.domain.GoScooterId
import pl.ccki.szypwyp.goscooter.domain.GoScooterRepository

@Module(includes = [GoScooterApiModule::class])
abstract class GoScooterModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun id() = GoScooterId
    }

    @Binds
    abstract fun repository(impl: RemoteGoScooterRepository): GoScooterRepository
}

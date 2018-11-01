package pl.ccki.szypwyp.blinkee.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.blinkee.data.RemoteBlinkeeRepository
import pl.ccki.szypwyp.blinkee.domain.BlinkeeId
import pl.ccki.szypwyp.blinkee.domain.BlinkeeRepository

@Module(includes = [BlinkeeApiModule::class])
abstract class BlinkeeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun id() = BlinkeeId
    }

    @Binds
    abstract fun repository(impl: RemoteBlinkeeRepository): BlinkeeRepository
}

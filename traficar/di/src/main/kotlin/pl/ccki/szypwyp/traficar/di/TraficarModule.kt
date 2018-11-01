package pl.ccki.szypwyp.traficar.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.traficar.data.RemoteTraficarRepository
import pl.ccki.szypwyp.traficar.domain.TraficarId
import pl.ccki.szypwyp.traficar.domain.TraficarRepository

@Module(includes = [TraficarApiModule::class])
abstract class TraficarModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun id() = TraficarId
    }

    @Binds
    abstract fun repository(impl: RemoteTraficarRepository): TraficarRepository
}

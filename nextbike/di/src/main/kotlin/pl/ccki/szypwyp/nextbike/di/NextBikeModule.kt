package pl.ccki.szypwyp.nextbike.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import pl.ccki.szypwyp.nextbike.data.RemoteNextbikeRepository
import pl.ccki.szypwyp.nextbike.domain.NextbikeId
import pl.ccki.szypwyp.nextbike.domain.NextbikeRepository

@Module(includes = [NextbikeApiModule::class])
abstract class NextBikeModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun id() = NextbikeId
    }

    @Binds
    abstract fun repository(impl: RemoteNextbikeRepository): NextbikeRepository
}

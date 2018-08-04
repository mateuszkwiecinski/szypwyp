package pl.ccki.szypwyp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.presentation.MapFragment

@Module
abstract class MainActivityInjector {
    @ContributesAndroidInjector
    abstract fun mapFragment(): MapFragment
}

package pl.ccki.szypwyp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.presentation.configuration.ui.ConfigurationFragment
import pl.ccki.szypwyp.presentation.map.ui.MapFragment

@Module
abstract class MainActivityInjector {

    @ContributesAndroidInjector
    abstract fun map(): MapFragment

    @ContributesAndroidInjector
    abstract fun configuration(): ConfigurationFragment
}

package pl.ccki.szypwyp.di.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.di.FragmentScope
import pl.ccki.szypwyp.di.main.configuration.ConfigurationModule
import pl.ccki.szypwyp.di.main.configuration.FiltersModule
import pl.ccki.szypwyp.di.main.configuration.LicensesModule
import pl.ccki.szypwyp.di.main.map.MapModule
import pl.ccki.szypwyp.presentation.configuration.ui.ConfigurationFragment
import pl.ccki.szypwyp.presentation.configuration.ui.LicensesFragment
import pl.ccki.szypwyp.presentation.filters.ui.FiltersFragment
import pl.ccki.szypwyp.presentation.map.ui.MapFragment

@Module
abstract class MainActivityInjector {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MapModule::class])
    abstract fun map(): MapFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ConfigurationModule::class])
    abstract fun configuration(): ConfigurationFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [FiltersModule::class])
    abstract fun filters(): FiltersFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [LicensesModule::class])
    abstract fun licenses(): LicensesFragment
}

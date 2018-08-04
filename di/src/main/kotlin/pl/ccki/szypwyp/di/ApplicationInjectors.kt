package pl.ccki.szypwyp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.presentation.MainActivity

@Module
abstract class ApplicationInjectors {

    @ContributesAndroidInjector(modules = [MainActivityInjector::class])
    abstract fun mainActivity(): MainActivity
}

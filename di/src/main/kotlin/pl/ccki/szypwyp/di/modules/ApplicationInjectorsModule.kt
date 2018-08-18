package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.di.MainActivityInjector
import pl.ccki.szypwyp.presentation.MainActivity

@Module
abstract class ApplicationInjectorsModule {

    @ContributesAndroidInjector(modules = [MainActivityInjector::class])
    abstract fun mainActivity(): MainActivity
}

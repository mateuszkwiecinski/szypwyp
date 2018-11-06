package pl.ccki.szypwyp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.ccki.szypwyp.di.ActivityScope
import pl.ccki.szypwyp.di.main.MainActivityInjector
import pl.ccki.szypwyp.presentation.MainActivity

@Module
abstract class ApplicationInjectorsModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityInjector::class])
    abstract fun mainActivity(): MainActivity
}

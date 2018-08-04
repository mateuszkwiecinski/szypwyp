package pl.ccki.szypwyp.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.ccki.szypwyp.blinkee.BlinkeeComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationInjectors::class,
    DataModule::class
], dependencies = [BlinkeeComponent::class])
interface ApplicationComponent : AndroidInjector<DIApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DIApplication>() {
        abstract fun blinkee(bm: BlinkeeComponent): Builder
    }

}
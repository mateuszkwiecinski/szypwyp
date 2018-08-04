package pl.ccki.szypwyp.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.ccki.szypwyp.blinkee.di.BlinkeeComponent
import pl.ccki.szypwyp.vozilla.di.VozillaComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationInjectors::class,
    DataModule::class
], dependencies = [BlinkeeComponent::class, VozillaComponent::class])
interface ApplicationComponent : AndroidInjector<DIApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DIApplication>(){
        abstract fun vozilla(dependency: VozillaComponent): Builder
        abstract fun blinkee(dependency: BlinkeeComponent): Builder
    }

}
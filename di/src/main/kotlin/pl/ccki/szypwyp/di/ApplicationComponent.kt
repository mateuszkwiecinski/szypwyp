package pl.ccki.szypwyp.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import pl.ccki.szypwyp.DIApplication
import pl.ccki.szypwyp.blinkee.di.BlinkeeComponent
import pl.ccki.szypwyp.di.modules.AppModule
import pl.ccki.szypwyp.di.modules.ApplicationInjectorsModule
import pl.ccki.szypwyp.di.modules.DebugModule
import pl.ccki.szypwyp.di.modules.PluginsModule
import pl.ccki.szypwyp.goscooter.di.GoScooterComponent
import pl.ccki.szypwyp.nextbike.di.NextbikeComponent
import pl.ccki.szypwyp.platform.PlatformComponent
import pl.ccki.szypwyp.traficar.di.TraficarComponent
import pl.ccki.szypwyp.vozilla.di.VozillaComponent
import javax.inject.Scope

@ApplicationSingleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationInjectorsModule::class,
    PluginsModule::class,
    AppModule::class,
    DebugModule::class
], dependencies = [
    VozillaComponent::class,
    BlinkeeComponent::class,
    GoScooterComponent::class,
    TraficarComponent::class,
    PlatformComponent::class,
    NextbikeComponent::class
])
interface ApplicationComponent : AndroidInjector<DIApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DIApplication>() {
        abstract fun platform(dependency: PlatformComponent): Builder
        abstract fun vozilla(dependency: VozillaComponent): Builder
        abstract fun blinkee(dependency: BlinkeeComponent): Builder
        abstract fun goScooter(dependency: GoScooterComponent): Builder
        abstract fun traficar(dependency: TraficarComponent): Builder
        abstract fun nextbike(dependency: NextbikeComponent): Builder
    }
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationSingleton

package pl.ccki.szypwyp.di.modules

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import pl.ccki.szypwyp.blinkee.domain.BlinkeeService
import pl.ccki.szypwyp.domain.services.ExternalService
import pl.ccki.szypwyp.goscooter.GoScooterService
import pl.ccki.szypwyp.traficar.TraficarService
import pl.ccki.szypwyp.vozilla.VozillaService

@Module
abstract class ServicesModule {

    @Binds
    @IntoSet
    abstract fun blinkee(service: BlinkeeService): ExternalService

    @Binds
    @IntoSet
    abstract fun traficar(service: TraficarService): ExternalService

    @Binds
    @IntoSet
    abstract fun vozilla(service: VozillaService): ExternalService

    @Binds
    @IntoSet
    abstract fun goScooter(service: GoScooterService): ExternalService
}

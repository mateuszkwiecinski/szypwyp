package pl.ccki.szypwyp.blinkee.di

import dagger.Component
import pl.ccki.szypwyp.blinkee.domain.BlinkeeService

@Component(modules = [BlinkeeModule::class])
interface BlinkeeComponent {

    fun service(): BlinkeeService
}

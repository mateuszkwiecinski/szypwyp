package pl.ccki.szypwyp.blinkee.di

import dagger.Component
import pl.ccki.szypwyp.blinkee.BlinkeeRepository

@Component(modules = [BlinkeeModule::class])
interface BlinkeeComponent {
    fun repository(): BlinkeeRepository
}

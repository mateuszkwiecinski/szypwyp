package pl.ccki.szypwyp.blinkee

import dagger.Component

@Component(modules = [BlinkeeModule::class])
interface BlinkeeComponent {
    fun repository(): BlinkeeRepository
}

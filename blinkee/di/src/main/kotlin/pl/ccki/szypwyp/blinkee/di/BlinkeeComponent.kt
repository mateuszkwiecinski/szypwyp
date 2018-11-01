package pl.ccki.szypwyp.blinkee.di

import dagger.Component
import pl.ccki.szypwyp.blinkee.domain.BlinkeeId
import pl.ccki.szypwyp.blinkee.domain.BlinkeePlugin
import pl.ccki.szypwyp.blinkee.presentation.BlinkeeMapViewsProvider

@Component(modules = [BlinkeeModule::class])
interface BlinkeeComponent {
    fun id(): BlinkeeId
    fun plugin(): BlinkeePlugin
    fun view(): BlinkeeMapViewsProvider
}

package pl.ccki.szypwyp.goscooter.di

import dagger.Component
import pl.ccki.szypwyp.goscooter.domain.GoScooterId
import pl.ccki.szypwyp.goscooter.domain.GoScooterPlugin
import pl.ccki.szypwyp.goscooter.presentation.GoScooterMapViewsProvider

@Component(modules = [GoScooterModule::class])
interface GoScooterComponent {
    fun id(): GoScooterId
    fun plugin(): GoScooterPlugin
    fun view(): GoScooterMapViewsProvider
}

package pl.ccki.szypwyp.goscooter.di

import dagger.Component
import pl.ccki.szypwyp.goscooter.domain.GoScooterService

@Component(modules = [GoScooterModule::class])
interface GoScooterComponent {
    fun service(): GoScooterService
}

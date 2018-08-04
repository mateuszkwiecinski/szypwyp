package pl.ccki.szypwyp.goscooter.di

import dagger.Component
import pl.ccki.szypwyp.goscooter.GoScooterRepository

@Component(modules = [GoScooterModule::class])
interface GoScooterComponent {
    fun repository(): GoScooterRepository
}

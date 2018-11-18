package pl.ccki.szypwyp.vozilla.di

import dagger.Component
import pl.ccki.szypwyp.vozilla.domain.VozillaId
import pl.ccki.szypwyp.vozilla.domain.VozillaPlugin
import pl.ccki.szypwyp.vozilla.presentation.VozillaMapViewsProvider
import pl.ccki.szypwyp.vozilla.presentation.filters.VozillaFilterViewsProvider

@Component(modules = [VozillaModule::class])
interface VozillaComponent {
    fun id(): VozillaId
    fun plugin(): VozillaPlugin
    fun map(): VozillaMapViewsProvider
    fun filter(): VozillaFilterViewsProvider
}

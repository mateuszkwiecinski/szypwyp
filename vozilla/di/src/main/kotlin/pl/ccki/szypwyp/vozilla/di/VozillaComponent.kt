package pl.ccki.szypwyp.vozilla.di

import dagger.Component
import pl.ccki.szypwyp.vozilla.domain.VozillaService

@Component(modules = [VozillaModule::class])
interface VozillaComponent {
    fun repository(): VozillaService
}

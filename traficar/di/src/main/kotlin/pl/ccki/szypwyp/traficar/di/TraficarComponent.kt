package pl.ccki.szypwyp.traficar.di

import dagger.Component
import pl.ccki.szypwyp.traficar.domain.TraficarService

@Component(modules = [TraficarModule::class])
interface TraficarComponent {
    fun service(): TraficarService
}

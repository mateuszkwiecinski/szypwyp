package pl.ccki.szypwyp.traficar.di

import dagger.Component
import pl.ccki.szypwyp.traficar.TraficarRepository

@Component(modules = [TraficarModule::class])
interface TraficarComponent {
    fun repository(): TraficarRepository
}

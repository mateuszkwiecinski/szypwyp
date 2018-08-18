package pl.ccki.szypwyp.traficar.di

import dagger.Component
import pl.ccki.szypwyp.traficar.TraficarService

@Component(modules = [TraficarModule::class])
interface TraficarComponent {

    fun service(): TraficarService
}

package pl.ccki.szypwyp.traficar.di

import dagger.Component
import pl.ccki.szypwyp.traficar.domain.TraficarId
import pl.ccki.szypwyp.traficar.domain.TraficarPlugin
import pl.ccki.szypwyp.traficar.presentation.TraficarMapViewsProvider

@Component(modules = [TraficarModule::class])
interface TraficarComponent {
    fun id(): TraficarId
    fun plugin(): TraficarPlugin
    fun view(): TraficarMapViewsProvider
}

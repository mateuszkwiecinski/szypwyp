package pl.ccki.szypwyp.nextbike.di

import dagger.Component
import pl.ccki.szypwyp.nextbike.domain.NextbikeId
import pl.ccki.szypwyp.nextbike.domain.NextbikePlugin
import pl.ccki.szypwyp.nextbike.presentation.NextbikeMapViewsProvider

@Component(modules = [NextBikeModule::class])
interface NextbikeComponent {
    fun id(): NextbikeId
    fun plugin(): NextbikePlugin
    fun view(): NextbikeMapViewsProvider
}

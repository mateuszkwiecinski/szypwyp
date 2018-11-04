package pl.ccki.szypwyp.blinkee.domain

import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeRegion
import pl.ccki.szypwyp.domain.models.MarkerModel

interface BlinkeeRepository {
    fun getAll(region: BlinkeeRegion): List<MarkerModel>
}

package pl.ccki.szypwyp.blinkee.domain

import pl.ccki.szypwyp.domain.models.MarkerModel

interface BlinkeeRepository {
    fun getAll(): List<MarkerModel>
}

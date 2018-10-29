package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.MarkerModel

interface VozillaRepository {
    fun getAll(): List<MarkerModel>
}

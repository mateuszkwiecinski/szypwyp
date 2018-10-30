package pl.ccki.szypwyp.goscooter.domain

import pl.ccki.szypwyp.domain.models.MarkerModel

interface GoScooterRepository {
    fun getAll(): List<MarkerModel>
}

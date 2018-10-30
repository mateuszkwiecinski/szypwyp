package pl.ccki.szypwyp.traficar.domain

import pl.ccki.szypwyp.domain.models.MarkerModel

interface TraficarRepository {
    fun getAll(): List<MarkerModel>
}

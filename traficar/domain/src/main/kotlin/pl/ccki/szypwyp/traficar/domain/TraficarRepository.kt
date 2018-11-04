package pl.ccki.szypwyp.traficar.domain

import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.traficar.domain.models.TraficarRegion

interface TraficarRepository {
    fun getAll(region: TraficarRegion): List<MarkerModel>
}

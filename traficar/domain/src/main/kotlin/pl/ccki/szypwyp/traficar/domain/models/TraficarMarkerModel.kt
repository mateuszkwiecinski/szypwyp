package pl.ccki.szypwyp.traficar.domain.models

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

data class TraficarMarkerModel(
    override val id: String,
    override val location: LatLng,
    val fuel: Double?
) : MarkerModel

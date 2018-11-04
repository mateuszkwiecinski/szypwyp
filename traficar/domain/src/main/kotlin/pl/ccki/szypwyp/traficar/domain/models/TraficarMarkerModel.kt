package pl.ccki.szypwyp.traficar.domain.models

import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Percent

data class TraficarMarkerModel(
    override val id: String,
    override val location: LatLng,
    override val name: String,
    val range: Kilometers?,
    val fuel: Percent?
) : MarkerModel

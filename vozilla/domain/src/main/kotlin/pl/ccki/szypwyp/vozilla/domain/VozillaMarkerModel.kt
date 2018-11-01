package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

data class VozillaMarkerModel(
    override val id: String,
    override val location: LatLng,
    val range: Kilometers?
) : MarkerModel

inline class Kilometers(val value: Int)

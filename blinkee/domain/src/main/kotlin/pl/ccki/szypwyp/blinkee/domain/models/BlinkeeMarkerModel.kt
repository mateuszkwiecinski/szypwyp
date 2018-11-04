package pl.ccki.szypwyp.blinkee.domain.models

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

data class BlinkeeMarkerModel(
    override val id: String,
    override val location: LatLng,
    override val name: String
) : MarkerModel

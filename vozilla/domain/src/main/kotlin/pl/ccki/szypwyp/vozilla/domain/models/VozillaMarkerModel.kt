package pl.ccki.szypwyp.vozilla.domain.models

import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.Percent

data class VozillaMarkerModel(
    override val id: String,
    override val location: LatLng,
    val name: String,
    val range: Kilometers?,
    val battery: Percent?
) : MarkerModel

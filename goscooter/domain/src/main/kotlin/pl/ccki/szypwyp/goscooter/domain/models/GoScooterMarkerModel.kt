package pl.ccki.szypwyp.goscooter.domain.models

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

data class GoScooterMarkerModel(
    override val id: String,
    override val location: LatLng
) : MarkerModel

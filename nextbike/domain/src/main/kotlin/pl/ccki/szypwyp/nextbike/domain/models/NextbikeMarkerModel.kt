package pl.ccki.szypwyp.nextbike.domain.models

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

data class NextbikeMarkerModel(
    override val id: String,
    override val location: LatLng,
    val address: String,
    val bikes: Int,
    val racks: Int,
    val number : Int?
) : MarkerModel {
    override val name: String
        get() = address
}

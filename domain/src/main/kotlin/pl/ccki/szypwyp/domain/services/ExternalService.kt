package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId

interface ExternalService {

    val id: ServiceId

    fun findInLocation(location: LatLng): List<MarkerModel>
}

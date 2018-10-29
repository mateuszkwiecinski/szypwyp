package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel

interface ExternalService {

    val info: ServiceInfoModel

    fun findInLocation(location: LatLng): List<MarkerModel>
}

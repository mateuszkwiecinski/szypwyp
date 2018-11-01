package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel

interface ExternalPlugin {
    val appId: ExternalAppId
    fun findInLocation(location: LatLng): List<MarkerModel>
}

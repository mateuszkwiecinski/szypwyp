package pl.ccki.szypwyp.domain.services

import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.MarkerModel

interface ExternalPlugin {
    val appId: ExternalAppId
    val supportedCities: Iterable<CityModel>
    fun findInLocation(location: CityId): List<MarkerModel>
}

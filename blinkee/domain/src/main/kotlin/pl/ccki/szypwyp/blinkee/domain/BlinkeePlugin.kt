package pl.ccki.szypwyp.blinkee.domain

import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class BlinkeePlugin @Inject constructor(
    private val repository: BlinkeeRepository
) : ExternalPlugin {

    override val appId = ExternalAppId("pl.blinkee.mobile")

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

package pl.ccki.szypwyp.goscooter.domain

import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class GoScooterPlugin @Inject constructor(
    private val repository: GoScooterRepository
) : ExternalPlugin {

    override val appId = ExternalAppId("pl.cybertrick.GoScooter")

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

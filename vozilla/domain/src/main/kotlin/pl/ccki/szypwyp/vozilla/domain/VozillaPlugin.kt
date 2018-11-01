package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class VozillaPlugin @Inject constructor(
    private val repository: VozillaRepository
) : ExternalPlugin {

    override val appId = ExternalAppId("pl.techgarden.vozilla")

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

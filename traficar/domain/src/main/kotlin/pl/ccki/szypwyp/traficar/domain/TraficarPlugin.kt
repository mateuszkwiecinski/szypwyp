package pl.ccki.szypwyp.traficar.domain

import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class TraficarPlugin @Inject constructor(
    private val repository: TraficarRepository
) : ExternalPlugin {
    override val appId = ExternalAppId("pl.express.traficar")

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

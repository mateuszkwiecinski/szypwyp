package pl.ccki.szypwyp.goscooter

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class GoScooterService @Inject constructor(
    private val repository: GoScooterRepository
) : ExternalService {

    override val id: ServiceId = "d7730c69-2a8f-goscooter-9ade-f82351404ee5"

    override fun findInLocation(location: LatLng): List<MarkerModel> = repository.getAll()
}
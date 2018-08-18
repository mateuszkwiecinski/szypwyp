package pl.ccki.szypwyp.blinkee

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceId
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class BlinkeeService @Inject constructor(
    private val repository: BlinkeeRepository
) : ExternalService {
    override val id: ServiceId = "780a0816-f227-blinkee-b48f-709380fa07bd"

    override fun findInLocation(location: LatLng): List<MarkerModel> = repository.getAll()
}

package pl.ccki.szypwyp.blinkee.domain

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class BlinkeeService @Inject constructor(
    private val repository: BlinkeeRepository,
    iconProvider: IconProvider
) : ExternalService {

    override val info = ServiceInfoModel(
        id = "780a0816-f227-blinkee-b48f-709380fa07bd",
        icon = iconProvider.icon
    )

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

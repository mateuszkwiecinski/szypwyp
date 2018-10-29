package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class VozillaService @Inject constructor(
    private val repository: VozillaRepository,
    iconProvider: IconProvider
) : ExternalService {

    override val info = ServiceInfoModel(
        id = "3ff8180f-73b3-vozilla-936f-0da81b967c7e",
        icon = iconProvider.icon
    )

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

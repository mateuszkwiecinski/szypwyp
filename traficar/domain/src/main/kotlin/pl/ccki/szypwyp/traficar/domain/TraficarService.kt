package pl.ccki.szypwyp.traficar.domain

import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.ServiceInfoModel
import pl.ccki.szypwyp.domain.services.ExternalService
import javax.inject.Inject

class TraficarService @Inject constructor(
    private val repository: TraficarRepository,
    iconProvider: IconProvider
) : ExternalService {

    override val info = ServiceInfoModel(
        id = "a21c0d56-13eb-traficar-ae54-25d3aca4354c",
        icon = iconProvider.icon
    )

    override fun findInLocation(location: LatLng): List<MarkerModel> =
        repository.getAll()
}

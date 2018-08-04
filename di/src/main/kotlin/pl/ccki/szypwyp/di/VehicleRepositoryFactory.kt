package pl.ccki.szypwyp.di

import pl.ccki.szypwyp.blinkee.BlinkeeRepository
import pl.ccki.szypwyp.domain.MarkerModel
import pl.ccki.szypwyp.domain.SearchModel
import pl.ccki.szypwyp.domain.ServiceType
import pl.ccki.szypwyp.domain.VehiclesRepository
import pl.ccki.szypwyp.vozilla.VozillaRepository
import javax.inject.Inject

class VehicleRepositoryFactory @Inject constructor(
    private val blinkee: BlinkeeRepository,
    private val vozilla: VozillaRepository
) : VehiclesRepository {
    override fun get(param: SearchModel): List<MarkerModel> =
        when (param.type) {
            ServiceType.GoScooter -> TODO()
            ServiceType.Vozilla -> vozilla.getAll()
            ServiceType.Traficar -> TODO()
            ServiceType.Nexbike -> TODO()
            ServiceType.Blinkee -> blinkee.getAll()
        }
}

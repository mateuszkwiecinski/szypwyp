package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.ExternalAppId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.cityWroclaw
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.vozilla.domain.models.VozillaRegion
import javax.inject.Inject

class VozillaPlugin @Inject constructor(
    private val repository: VozillaRepository
) : ExternalPlugin {

    override val appId = ExternalAppId("pl.techgarden.vozilla")

    override val supportedCities
        get() = listOf(cityWroclaw(VozillaRegion.Wroclaw))

    override fun findInLocation(location: CityId): List<MarkerModel> =
        repository.getAll()
}

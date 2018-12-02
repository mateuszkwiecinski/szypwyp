package pl.ccki.szypwyp.vozilla.domain

import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.cityWroclaw
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.vozilla.domain.models.VozillaRegion
import javax.inject.Inject

class VozillaPlugin @Inject constructor(
    private val repository: VozillaRepository
) : ExternalPlugin {

    override val appId = AppId("pl.techgarden.vozilla")

    override val name = "DVozilla"

    override val supportedCities
        get() = listOf(cityWroclaw(VozillaRegion.Wroclaw))

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> =
        repository.getAll()
}

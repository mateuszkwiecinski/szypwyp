package pl.ccki.szypwyp.goscooter.domain

import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.cityWroclaw
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.goscooter.domain.models.GoScooterRegion
import javax.inject.Inject

class GoScooterPlugin @Inject constructor(
    private val repository: GoScooterRepository
) : ExternalPlugin {

    override val appId = AppId("pl.cybertrick.GoScooter")

    override val supportedCities
        get() = listOf(cityWroclaw(GoScooterRegion.Wroclaw))

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> =
        repository.getAll()
}

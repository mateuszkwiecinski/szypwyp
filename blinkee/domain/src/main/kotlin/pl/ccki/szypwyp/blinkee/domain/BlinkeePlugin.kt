package pl.ccki.szypwyp.blinkee.domain

import pl.ccki.szypwyp.blinkee.domain.models.BlinkeeRegion
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.cityKrakow
import pl.ccki.szypwyp.domain.models.cityLodz
import pl.ccki.szypwyp.domain.models.cityLublin
import pl.ccki.szypwyp.domain.models.cityPoznan
import pl.ccki.szypwyp.domain.models.cityTrojmiasto
import pl.ccki.szypwyp.domain.models.cityWarsaw
import pl.ccki.szypwyp.domain.models.cityWroclaw
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import javax.inject.Inject

class BlinkeePlugin @Inject constructor(
    private val repository: BlinkeeRepository
) : ExternalPlugin {

    override val appId = AppId("pl.blinkee.mobile")

    override val supportedCities
        get() = listOf(
            cityWroclaw(BlinkeeRegion.Wroclaw),
            cityWarsaw(BlinkeeRegion.Warszawa),
                cityPoznan(BlinkeeRegion.Poznan),
                cityKrakow(BlinkeeRegion.Krakow),
                cityTrojmiasto(BlinkeeRegion.Trojmiasto),
                cityWroclaw(BlinkeeRegion.Wroclaw),
                cityLodz(BlinkeeRegion.Lodz),
                cityLublin(BlinkeeRegion.Lublin)
        )

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> =
        repository.getAll(location.map { it as BlinkeeRegion })
}

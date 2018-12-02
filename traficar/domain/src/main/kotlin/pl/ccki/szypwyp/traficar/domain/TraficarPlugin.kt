package pl.ccki.szypwyp.traficar.domain

import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.cityBydgoszcz
import pl.ccki.szypwyp.domain.models.cityKatowice
import pl.ccki.szypwyp.domain.models.cityKrakow
import pl.ccki.szypwyp.domain.models.cityLodz
import pl.ccki.szypwyp.domain.models.cityLublin
import pl.ccki.szypwyp.domain.models.cityPoznan
import pl.ccki.szypwyp.domain.models.cityTrojmiasto
import pl.ccki.szypwyp.domain.models.cityWarsaw
import pl.ccki.szypwyp.domain.models.cityWroclaw
import pl.ccki.szypwyp.domain.services.ExternalPlugin
import pl.ccki.szypwyp.traficar.domain.models.TraficarRegion
import javax.inject.Inject

class TraficarPlugin @Inject constructor(
    private val repository: TraficarRepository
) : ExternalPlugin {

    override val appId = AppId("pl.express.traficar")

    override val name = "Traficar"

    override val supportedCities
        get() = listOf(
            cityWroclaw(TraficarRegion.Wroclaw),
            cityWarsaw(TraficarRegion.Warszawa),
            cityKrakow(TraficarRegion.Krakow),
            cityPoznan(TraficarRegion.Poznan),
            cityLodz(TraficarRegion.Lodz),
            cityTrojmiasto(TraficarRegion.Gdansk),
            cityKatowice(TraficarRegion.Katowice),
            cityBydgoszcz(TraficarRegion.Bydgoszcz),
            cityLublin(TraficarRegion.Lublin)
        )

    override fun findInLocation(location: Iterable<CityId>): List<MarkerModel> =
        repository.getAll(location.map { it as TraficarRegion })
}

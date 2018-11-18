package pl.ccki.szypwyp.domain.utils

import pl.ccki.szypwyp.domain.models.AppId
import pl.ccki.szypwyp.domain.models.CityId
import pl.ccki.szypwyp.domain.models.CityModel
import pl.ccki.szypwyp.domain.models.Kilometers
import pl.ccki.szypwyp.domain.models.LatLng
import pl.ccki.szypwyp.domain.models.MarkerModel
import pl.ccki.szypwyp.domain.models.PluginId
import pl.ccki.szypwyp.domain.services.ExternalPlugin

internal fun createPlugin(pluginId: String, locations: List<CityModel>, data: () -> List<MarkerModel>): Pair<PluginId,
    ExternalPlugin> {
    val first = Id(pluginId)
    val second = object : ExternalPlugin {
        override val appId: AppId
            get() = AppId("pl.$pluginId")
        override val supportedCities: Iterable<CityModel>
            get() = locations

        override fun findInLocation(location: Iterable<CityId>) = data()
    }

    return first to second
}

val WORLD = CityModel(Id("world"), LatLng(0.0, 0.0), radius = Kilometers(Long.MAX_VALUE))

internal data class Id(override val id: String) : CityId, PluginId
